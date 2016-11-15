package services;

import domain.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ConfigurationRepository;
import security.UserAccountService;

@Service
@Transactional
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private UserAccountService userAccountService;

    public ConfigurationService(){
        super();
    }

    public Configuration changeBannersCost(double cost){

        Configuration config = configurationRepository.findAll().get(1);

        config.setFee(cost);
        return configurationRepository.save(config);

    }

    public Configuration changeBannersCost(){

        userAccountService.assertRole("ADMINISTRATOR");

        Configuration config = configurationRepository.findAll().get(1);

        config.setFee(0.25);
        return configurationRepository.save(config);

    }


}
