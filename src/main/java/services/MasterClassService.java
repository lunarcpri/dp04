package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.MasterClassRepository;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class MasterClassService {

    @Autowired
    private MasterClassRepository masterClassRepository;

    @Autowired
    private CookService cookService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;



    public MasterClassService(){

        super();
    }

    public void save(MasterClass masterClass){

        Assert.notNull(masterClass);

        masterClassRepository.save(masterClass);

    }

    private void delete(MasterClass masterClass){

        Assert.notNull(masterClass);
        masterClassRepository.delete(masterClass.getId());

    }

    public Collection<MasterClass> list(){

        return masterClassRepository.findAll();

    }

    public MasterClass newMasterClass(MasterClass masterClass){


        Assert.notNull(masterClass);
        userAccountService.assertRole("COOK");
        MasterClass result = masterClass;

        result.setCook((Cook) cookService.findByPrincipal());

        save(result);

        return result;

    }

    public MasterClass modifyMasterClass(MasterClass masterClass, String title, String description, Collection<LearningMaterial> learningMaterials){


        userAccountService.assertRole("COOK");
        Assert.notNull(masterClass);
        MasterClass modified = masterClass;

        modified.setTitle(title);
        modified.setDescription(description);
        modified.setLearningMaterials(learningMaterials);

        save(modified);

        return modified;

    }

    public void deleteMasterClass(MasterClass masterClass){

        userAccountService.assertRole("COOK");
        delete(masterClass);

        Message message = new Message();

        message.setPriority(Message.Priority.HIGH);
        message.setBody("SYSTEM MESSAGE: The master class "+masterClass.getTitle()+" was deleted.");

        for(User u:masterClass.getAttendingUsers()) {
            message.setRecipient(u);
            messageService.saveMessage(message);
        }
    }

    public void attendMasterClass(MasterClass masterClass){

        userAccountService.assertRole("USER");
        User user = userService.findByPrincipal();
        Assert.isTrue(!masterClass.getAttendingUsers().contains(user));

        MasterClass result = masterClass;

        Collection<User> currentlyAttending = result.getAttendingUsers();
        currentlyAttending.add(user);
        result.setAttendingUsers(currentlyAttending);

        save(result);

    }


}
