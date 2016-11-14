package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.PresentationMaterialRepository;

@Service
@Transactional
public class PresentationMaterialService {

    @Autowired
    private PresentationMaterialRepository presentationMaterialRepository;


    public PresentationMaterialService(){
        super();
    }
}
