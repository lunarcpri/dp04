package services;

import domain.Cook;
import domain.MasterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CookRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class CookService {

    @Autowired
    private CookRepository cookRepository;

    @Autowired
    private UserAccountService userAccountServiceServic;

    @Autowired
    private ActorService actorService;

    public CookService(){

        super();
    }

    public Cook save(Cook cook){

        Assert.notNull(cook);

        return cookRepository.save(cook);

    }

    public Collection<MasterClass> findCookMasterClasses(Cook c){

        userAccountServiceServic.assertRole("COOK");
        return c.getMasterClasses();

    }

    public Cook findByPrincipal() {
        Cook result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result =cookRepository.findByCookAccountId(userAccount.getId());

        return result;
    }



}
