package services;

import domain.CreditCard;
import domain.Nutritionist;
import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    FolderService folderService;

    @Autowired
    CreditCardService creditCardService;

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

    public void create(Sponsor s){
        Assert.notNull(s);
        List<Authority> authorities = new ArrayList<Authority>();
        Authority a = new Authority();
        a.setAuthority("SPONSOR");
        authorities.add(a);
        s.getUserAccount().setAuthorities(authorities);
        if (s.getCreditCard()!=null){
            System.out.println("ey");
            CreditCard c = creditCardService.save(s.getCreditCard());
            s.setCreditCard(c);
        }
        s = sponsorRepository.save(s);
        Assert.notNull(s);
        folderService.createDefaultFolders(s);
    }

    public Sponsor save(Sponsor s){
        Assert.notNull(s);

        return sponsorRepository.save(s);
    }
}
