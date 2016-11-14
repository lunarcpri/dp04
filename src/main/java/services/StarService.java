package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.StarRepository;

@Service
@Transactional
public class StarService {

    @Autowired
    private StarRepository starRepository;

    public StarService(){
        super();
    }
}
