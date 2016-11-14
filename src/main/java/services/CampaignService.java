package services;

import domain.Campaign;
import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CampaignRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private SponsorService sponsorService;

    public CampaignService(){
        super();
    }


    public Campaign create(Campaign c){

        return this.save(c);

    }

    private Campaign save(Campaign c){

        Assert.notNull(c);


        c.setSponsor(sponsorService.getActualSponsor());

        return campaignRepository.save(c);

    }

    public Campaign modify(int ID, Campaign c){


        Assert.isTrue(c.getSponsor().equals(sponsorService.getActualSponsor()));
        Assert.isTrue(!isActive(c) && !hasPassed(c));

        Campaign campaign = campaignRepository.findOne(ID);

        campaign.setBanners(c.getBanners());
        campaign.setEnd_at(c.getEnd_at());
        campaign.setStart_at(c.getStart_at());
        campaign.setStar_campaign(c.isStar_campaign());

        return this.save(campaign);
    }


    public void delete(int ID){

        Campaign campaign = campaignRepository.findOne(ID);

        Assert.isTrue(campaign.getSponsor().equals(sponsorService.getActualSponsor()));
        Assert.isTrue(!isActive(campaign) && !hasPassed(campaign));

        campaignRepository.delete(ID);

    }

    public Collection<Campaign> list(){

        return sponsorService.getActualSponsor().getCampaigns();

    }


    private boolean isActive(Campaign c){

        Date actualDate = new Date();
        return c.getStart_at().before(actualDate) && c.getEnd_at().after(actualDate);
    }

    private boolean hasPassed(Campaign c){

        Date actualDate = new Date();
        return c.getStart_at().before(actualDate);
    }
}
