package services;

import domain.Actor;
import domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.AdministratorRepository;
import security.UserAccount;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class AdministratorService {
        // Managed repository -----------------------------------------------------

        @Autowired
        private AdministratorRepository administratorRepository;

        // Supporting services ----------------------------------------------------

        @Autowired
        private UserAccountService userAccountService;

        // Constructors -----------------------------------------------------------

        public AdministratorService(){
            super();
        }

        // Simple CRUD methods ----------------------------------------------------


    public Collection<Administrator> findAll()
    {
        return administratorRepository.findAll();
    }
}

