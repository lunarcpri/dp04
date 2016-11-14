package services;

import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.SponsorRepository;

@Service
@Transactional
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private ActorService actorService;

    public SponsorService(){
        super();
    }

    public Sponsor getActualSponsor(){

        return (Sponsor) actorService.findByPrincipal();
    }
}
