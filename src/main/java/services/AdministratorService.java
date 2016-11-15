package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AdministratorRepository;
import repositories.SponsorRepository;
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
    private MessageService messageService;

    @Autowired
    private MasterClassService masterClassService;

    @Autowired
    private SponsorRepository sponsorRepository;

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


    public void notifyToSponsorsWithUnpaidBills(){

        userAccountService.assertRole("ADMINISTRATOR");

        Collection<Sponsor> sponsors = sponsorRepository.getSponsorsWithUpaidBills();

        Message message = new Message();
        message.setBody("You have unpaid bills");
        messageService.newMessage(sponsors,message);


    }





}

