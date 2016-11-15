package services;

import domain.Actor;
import domain.MasterClass;
import domain.Message;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.MasterClassRepository;
import security.UserAccountService;

import java.util.Collection;
import java.util.List;

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

    public MasterClass findOne(int id){
        MasterClass result;

        result = masterClassRepository.findOne(id);
        Assert.notNull(result);

        return result;
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

        result.setCook(cookService.findByPrincipal());

        save(result);

        return result;

    }

    public MasterClass modifyMasterClass(int id, MasterClass masterClass){


        userAccountService.assertRole("COOK");
        Assert.notNull(masterClass);
        MasterClass modified = findOne(id);

        modified.setTitle(masterClass.getTitle());
        modified.setDescription(masterClass.getDescription());
        modified.setLearningMaterials(masterClass.getLearningMaterials());

        save(modified);

        return modified;

    }

    public void deleteMasterClass(MasterClass masterClass){

        userAccountService.assertRole("COOK");
        delete(masterClass);

            Message message = new Message();

            message.setPriority(Message.Priority.HIGH);
            message.setBody("The master class "+masterClass.getTitle()+" was deleted by its owner. We are really sorry");
            message.setSubject("SYSTEM MESSAGE: The master class "+masterClass.getTitle()+" was deleted.");
            message.setRecipients(masterClass.getAttendingUsers());
            messageService.saveMessage(message);
    }

    public void attendMasterClass(MasterClass masterClass){

        userAccountService.assertRole("USER");
        User user = userService.findByPrincipal();
        Assert.isTrue(!masterClass.getAttendingUsers().contains(user));

        MasterClass result = masterClass;

        Collection<Actor> currentlyAttending = result.getAttendingUsers();
        currentlyAttending.add(user);
        result.setAttendingUsers(currentlyAttending);

        save(result);

    }

    public List<Object[]> findMinMaxAvgStddevMasterClassesPerCook(){
        List<Object[]> result;

        result = masterClassRepository.findMinMaxAvgStddevMasterClassesPerCook();
        Assert.notNull(result);

        return result;
    }

    public   Integer numberOfPromotedMasterClasses(){
        Integer result;

        result = masterClassRepository.numberOfPromotedMasterClasses();
        Assert.notNull(result);

        return result;
    }

    List<Double> AvgLearningMaterialsPerMasterClassGroupByLearningMaterialKind(){
        List<Double> result;

        result = masterClassRepository.AvgLearningMaterialsPerMasterClassGroupByLearningMaterialKind();
        Assert.notNull(result);

        return  result;
    }

    Double[] findAvgPromotedDemotedMasterClasses(){
        Double[] result;

        result = masterClassRepository.findAvgPromotedDemotedMasterClasses();
        Assert.notNull(result);

        return result;
    }

    public void promoteMasterClass(MasterClass masterClass){

        userAccountService.assertRole("ADMINISTRATOR");
        Assert.notNull(masterClass);
        Assert.isTrue(!masterClass.isPromoted());

        MasterClass result = masterClass;
        result.setPromoted(true);

        save(result);


    }

    public void demoteMasterClass(MasterClass masterClass){


        userAccountService.assertRole("ADMINISTRATOR");
        Assert.notNull(masterClass);
        Assert.isTrue(masterClass.isPromoted());

        MasterClass result = masterClass;
        result.setPromoted(false);

        save(result);
    }


}
