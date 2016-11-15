package services;

import domain.Actor;
import domain.Cook;
import domain.DomainEntity;
import domain.MasterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CookRepository;
import security.Authority;
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

    @Autowired
    private UserAccountService userAccountService;

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



}
