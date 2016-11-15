package services;

import domain.SocialIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.SocialIdentityRepository;

import java.util.Collection;

@Service
@Transactional
public class SocialIdentityService {

    @Autowired
    private SocialIdentityRepository socialIdentityRepository;

    @Autowired
    private UserService userService;


    public SocialIdentityService(){
        super();
    }

    public SocialIdentity create(SocialIdentity socialIdentity){
        SocialIdentity result;

        result = new SocialIdentity();

        return result;
    }

    public SocialIdentity findOne(int id){
        SocialIdentity result;

        result = socialIdentityRepository.findOne(id);

        return result;
    }

    public Collection<SocialIdentity> findAll(){
        Collection<SocialIdentity> result;

        Assert.notNull(socialIdentityRepository);
        result = socialIdentityRepository.findAll();
        Assert.notNull(result);

        return result;
    }


    public void save(SocialIdentity socialIdentity){
        Assert.notNull(socialIdentity);

        socialIdentityRepository.save(socialIdentity);
    }
}
