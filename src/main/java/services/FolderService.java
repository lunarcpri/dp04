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
import repositories.UserRepository;

import java.util.Collection;

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


    public Folder createCustomFolder(Folder folder){

        User user = userService.findByPrincipal();
        Assert.notNull(user);
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

    public Folder findFolderByMessageAndActor(Message message, Actor actor){
        Collection<Folder> foldersActor = actor.getFolders();
        Folder folder = null;
        for(Folder e:foldersActor){
            if (e.getMessages().contains(message)){
                folder = e;
            }
        }
        return folder;
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
