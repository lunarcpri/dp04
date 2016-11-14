package services;

import domain.MasterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.MasterClassRepository;

import java.util.Collection;

@Service
@Transactional
public class MasterClassService {

    @Autowired
    private MasterClassRepository masterClassRepository;



    public MasterClassService(){

        super();
    }

    public Collection<MasterClass> findMasterClasses(){

        return masterClassRepository.findAll();

    }
}
