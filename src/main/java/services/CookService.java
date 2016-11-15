package services;

import domain.Cook;
import domain.DomainEntity;
import domain.MasterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CookRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    List<Cook> findCooksOrderByPromotedMasterClasses(){
        List<Cook> result = new ArrayList<Cook>();
        List<Object[]> sqlResult = cookRepository.findCooksOrderByPromotedMasterClasses();
        Assert.notNull(sqlResult);
        for(Object[] e: sqlResult){
            result.add((Cook) e[0]);
        }
        Assert.notNull(result);

        return result;
    }



}
