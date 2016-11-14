package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.MasterClassRepository;

@Service
@Transactional
public class MasterClassService {

    @Autowired
    private MasterClassRepository masterClassRepository;

    public MasterClassService(){
        super();
    }
}
