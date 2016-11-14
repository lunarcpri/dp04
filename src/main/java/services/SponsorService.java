package services;

import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.SponsorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    public SponsorService(){
        super();
    }

    public Sponsor findByPrincipal() {
        Sponsor result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = sponsorRepository.findBySponsorAccountId(userAccount.getId());

        return result;
    }
}
