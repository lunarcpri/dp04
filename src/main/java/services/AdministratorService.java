package services;

import domain.Actor;
import domain.Administrator;
import domain.Cook;
import domain.MasterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class AdministratorService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private AdministratorRepository administratorRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MasterClassService masterClassService;

    @Autowired
    private ActorService actorService;
    // Constructors -----------------------------------------------------------

    public AdministratorService(){

        super();
    }

    // Simple CRUD methods ----------------------------------------------------


    public Collection<Administrator> findAll()
    {
        return administratorRepository.findAll();
    }

    public Administrator findByPrincipal() {
        Administrator result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = administratorRepository.findByAdministratorAccountId(userAccount.getId());

        return result;
    }

    public void registerNewCook(Actor actor){

        userAccountService.assertRole("ADMINISTRATOR");
        Assert.notNull(actor);

        Actor result = actor;

        Collection<Authority> authorities = result.getUserAccount().getAuthorities();
        Assert.isTrue(!authorities.contains("COOK"));

        Authority authority = new Authority();
        authority.setAuthority("COOK");

        authorities.add(authority);
        actor.getUserAccount().setAuthorities(authorities);

        actorService.save(result);

//          Cook cook = (Cook) actor;
//          return cookService.save(cook);

    }

    public void promoteMasterClass(MasterClass masterClass){

        userAccountService.assertRole("ADMINISTRATOR");
        Assert.notNull(masterClass);
        Assert.isTrue(!masterClass.isPromoted());

        MasterClass result = masterClass;
        result.setPromoted(true);

        masterClassService.save(result);


    }

    public void demoteMasterClass(MasterClass masterClass){


        userAccountService.assertRole("ADMINISTRATOR");
        Assert.notNull(masterClass);
        Assert.isTrue(masterClass.isPromoted());

        MasterClass result = masterClass;
        result.setPromoted(false);

        masterClassService.save(result);
    }

}

