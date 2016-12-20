package services;


import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.BillRepository;
import repositories.MessageRepository;
import security.UserAccountService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageService {

    @Autowired
    private ActorService actorService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private FolderService folderService;

    @Autowired
    private SpamTagsService spamTagsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;



    public MessageService(){
        super();
    }



    public Message findOne(int messageId){
        Message result;

        result = messageRepository.findOne(messageId);
        Assert.notNull(result);

        return result;
    }

    public Message create(){
        Message result;

        result = new Message();

        return result;
    }

    public void delete(Message message){
        Assert.notNull(message);

        messageRepository.delete(message.getId());
    }

    public void deleteById(int messageId){
        Actor actor = userService.findByPrincipal();
        Message message = findOne(messageId);
        Folder folder = null;
        if (message.getSender() == actor || message.getRecipients().contains(actor)){
            folder = folderService.findFolderByMessageAndActor(message.getId(),actor.getId());
            if (folder.getFolderType() != Folder.FolderType.THRASHBOX){
                folder.setFolderType(Folder.FolderType.THRASHBOX);
            }else{

                delete(message);
            }
        }

    }



    public Message newMessage(Message message){

        Date sendedAt = new Date();
        Actor senderActor = actorService.findActorByPrincipal();
        Assert.notNull(senderActor);
        message.setSender(senderActor);
        message.setSended_at(sendedAt);
        message = save(message);
        List<Folder> folders = setFolders(message,senderActor,message.getRecipients());
        message.setFolders(folders);
        return save(message);
    }



    private Message save(Message message){
        Assert.notNull(message);

        return messageRepository.save(message);
    }

    public void saveMessage(Message message){

        save(message);

    }

    public void moveMessage(int messageId,int folderId){
        Folder newFolder = folderService.findOne(folderId);
        Message message = findOne(messageId);
        Actor actor = userService.findByPrincipal();
        Assert.notNull(newFolder);
        Assert.notNull(message);
        Assert.notNull(actor);
        Folder actualFolder = folderService.findFolderByMessageAndActor(actor.getId(),message.getId());
        folderService.removeMessage(actualFolder.getId(),message);
        folderService.addMessage(newFolder.getId(),message);
    }

    private List<Folder> setFolders(Message message, Actor senderActor, Collection<Actor> recipientsActor){
        List<Folder> result = new ArrayList<Folder>();
        Folder folderSender = folderService.findOutbox(senderActor.getId());
        Assert.notNull(folderSender);
        for(Actor e : recipientsActor){
            Folder folderRecipient = folderService.findInbox(e.getId());
            if (isMessageSpam(message)) {
                folderRecipient = folderService.findSpambox(e.getId());
            }
            Assert.notNull(folderRecipient);
            result.add(folderRecipient);
            folderService.addMessage(folderRecipient.getId(),message);
        }
        result.add(folderSender);
        folderService.addMessage(folderSender.getId(),message);
        return result;
    }


   public Collection<Message> findAll(){
        Collection<Message> result;

        result = messageRepository.findAll();
        Assert.notNull(result);

        return result;
    }
    private boolean isMessageSpam(Message message){
        boolean result = false;
        Collection<SpamTags> spamTagses = spamTagsService.findAll();

        for(SpamTags e: spamTagses){
            if (message.getBody().toLowerCase().contains(e.getName()) ||
                    message.getSubject().toLowerCase().contains(e.getName())){
                result = true;
            }
        }
        return result;
    }

    public Collection<Message> findAllPrincipal(){
        Collection<Message> result;

        Actor a = userService.findByPrincipal();
        result = messageRepository.findAllByActor(a.getId());
        Assert.notNull(result);

        return result;
    }



}
