package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.LearningMaterialRepository;

@Service
@Transactional
public class LearningMaterialService {

    @Autowired
    private LearningMaterialRepository learningMaterialRepository;

    public LearningMaterialService(){
        super();
    }
}


