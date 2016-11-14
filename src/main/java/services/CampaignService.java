package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CampaignRepository;

@Service
@Transactional
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public CampaignService(){
        super();
    }
}
