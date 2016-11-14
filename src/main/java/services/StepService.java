package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.StepRepository;

@Service
@Transactional
public class StepService {

    @Autowired
    private StepRepository stepRepository;


    public StepService(){
        super();
    }
}
