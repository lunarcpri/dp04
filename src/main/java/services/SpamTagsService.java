package services;

import domain.Actor;
import domain.SpamTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.SpamTagsRepository;
import security.Authority;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class SpamTagsService {

    @Autowired
    private SpamTagsRepository spamTagsRepository;


    @Autowired
    private UserAccountService userAccountService;



    public SpamTagsService(){
        super();
    }

    public Collection<SpamTags> findAll(){
        Assert.notNull(spamTagsRepository);

        return spamTagsRepository.findAll();
    }

    public SpamTags create(){
        return new SpamTags();
    }

    public void addSpamTag(String tag){
        userAccountService.assertRole("Admin");


    }
}
