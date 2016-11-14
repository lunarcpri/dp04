package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ReferenceRepository;

@Service
@Transactional
public class ReferenceService {

    @Autowired
    private ReferenceRepository referenceRepository;


    public ReferenceService(){
        super();
    }
}
