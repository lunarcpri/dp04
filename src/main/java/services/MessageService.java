package services;

import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.SpamTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.MessageRepository;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private FolderService folderService;

    @Autowired
    private SpamTagsService spamTagsService;

    @Autowired
    private UserService userService;


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
        if (message.getSender() == actor || message.getRecipient() == actor){
            folder = folderService.findFolderByMessageAndActor(message,actor);
            if (folder.getFolderType() != Folder.FolderType.THRASHBOX){
                folder.setFolderType(Folder.FolderType.THRASHBOX);
            }else{
                delete(message);
            }
        }

    }



    public Message newMessage(int recipientId, Message message){
        Message result;

        Date sendedAt = new Date();
        Actor senderActor = userService.findByPrincipal();
        Assert.notNull(senderActor);
        Actor recipientActor = userService.findOne(recipientId);
        Assert.notNull(recipientActor);
        message.setRecipient(recipientActor);
        message.setSender(senderActor);
        message.setSended_at(sendedAt);
        setFolder(message,senderActor,recipientActor);
        save(message);
        return message;
    }



    private void save(Message message){
        Assert.notNull(message);

        messageRepository.save(message);
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
        Folder actualFolder = folderService.findFolderByMessageAndActor(message,actor);
        folderService.removeMessage(actualFolder.getId(),message);
        folderService.addMessage(newFolder.getId(),message);
    }

    private void setFolder(Message message, Actor senderActor, Actor recipientActor){
        Folder folderSender = folderService.findOutbox(senderActor.getId());
        Assert.notNull(folderSender);
        Folder folderRecipient = folderService.findInbox(recipientActor.getId());
        if (isMessageSpam(message)) {
            folderRecipient = folderService.findSpambox(recipientActor.getId());
        }
        Assert.notNull(folderRecipient);
        folderService.addMessage(folderRecipient.getId(),message);
        folderService.addMessage(folderSender.getId(),message);
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
