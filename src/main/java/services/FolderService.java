package services;

import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.FolderRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private UserService userService;


    public FolderService(){
        super();
    }



    public Folder findOne(int folderId){
        Folder result;

        result = folderRepository.findOne(folderId);
        Assert.notNull(result);

        return result;
    }

    public Collection<Folder> findAll(){
        Collection<Folder> result;

        result = folderRepository.findAll();
        Assert.notNull(result);

        return result;
    }


    public void delete(int folderIt){
        Folder folder;

        folder = folderRepository.findOne(folderIt);
        Assert.notNull(folder);

        folderRepository.delete(folder);
    }


    private void save(Folder folder){
        Assert.notNull(folder);

        folderRepository.save(folder);
    }



    private Folder create(){
        Folder result;

        result = new Folder();

        return result;
    }


    public Collection<Folder> findAllCustomFolderByPrincipal(){
        Collection<Folder> result;
        Actor user = userService.findByPrincipal();
        result = folderRepository.findCustomFoldersByActorId(user.getId());
        Assert.notNull(result);

        return  result;
    }

    public Folder createCustomFolder(Folder folder){

        Actor user = userService.findByPrincipal();
        folder.setActor(user);
        folder.setFolderType(Folder.FolderType.CUSTOM);
        save(folder);

        return folder;
    }

    public void editCustomFolder(int folderId, String name){
        Folder folder = findOne(folderId);
        folder.setName(name);

        save(folder);
    }

    public void createDefaultFolders(Actor u){
        Folder inbox = create();
        inbox.setName("Inbox");
        inbox.setFolderType(Folder.FolderType.INBOX);
        Folder outbox = create();
        outbox.setName("Outbox");
        outbox.setFolderType(Folder.FolderType.OUTBOX);
        Folder spambox = create();
        spambox.setName("Spambox");
        spambox.setFolderType(Folder.FolderType.SPAMBOX);
        Folder trashbox = create();
        trashbox.setName("Trashbox");
        trashbox.setFolderType(Folder.FolderType.THRASHBOX);
        inbox.setActor(u);
        outbox.setActor(u);
        spambox.setActor(u);
        trashbox.setActor(u);
        save(inbox);
        save(outbox);
        save(spambox);
        save(trashbox);

    }

    public Folder findFolderByMessageAndActor(int actorid, int messageid){
        Folder result;

        result = folderRepository.findFolderByMessageAndActor(actorid,messageid);
        Assert.notNull(result);

        return result;
    }

    public Folder findInbox(int id){
        Folder result;

        result = folderRepository.findInboxFolderByActorId(id);
        Assert.notNull(result);

        return result;
    }

    public Folder findSpambox(int id){
        Folder result;

        result = folderRepository.findSpamboxFolderByActorId(id);
        Assert.notNull(result);

        return result;
    }

    public Folder findOutbox(int id){
        Folder result;

        result = folderRepository.findOutboxFolderByActorId(id);
        Assert.notNull(result);

        return result;
    }

    public Folder findTrashbox(int id){
        Folder result;

        result = folderRepository.findTrashboxFolderByActorId(id);
        Assert.notNull(result);

        return result;
    }

    public void addMessage(int id, Message message){
        Folder folder = folderRepository.findOne(id);
        Assert.notNull(folder);
        folder.getMessages().add(message);
        Collection<Message> newMessages = folder.getMessages();
        folder.setMessages(newMessages);
        save(folder);
    }

    public void removeMessage(int id,Message message){
        Folder folder = folderRepository.findOne(id);
        folder.getMessages().remove(message);
        Collection<Message> newMessages = folder.getMessages();
        folder.setMessages(newMessages);
        save(folder);
    }



}
