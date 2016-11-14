package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.BannerRepository;

@Service
@Transactional
public class BannerService {


    private BannerRepository bannerRepository;

    @Autowired
    public BannerService(BannerRepository bannerRepository){
        this.bannerRepository = bannerRepository;
    }
}
