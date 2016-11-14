package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCardService(){
        super();
    }
}
