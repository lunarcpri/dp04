package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CookRepository;

@Service
@Transactional
public class CookService {

    @Autowired
    private CookRepository cookRepository;

    public CookService(){
        super();
    }
}
