package services;

import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private SponsorService sponsorService;

    public CreditCardService(){
        super();
    }

    public CreditCard change(CreditCard card){

        Assert.notNull(card);
        Assert.isTrue(card == sponsorService.findByPrincipal().getCreditCard());

        CreditCard actualCard = sponsorService.findByPrincipal().getCreditCard();

        actualCard.setBrand_name(card.getBrand_name());
        actualCard.setCvv(card.getCvv());
        actualCard.setExpired_month(card.getExpired_month());
        actualCard.setExpired_year(card.getExpired_year());
        actualCard.setNumber(card.getNumber());
        actualCard.setHolder_name(card.getHolder_name());

        return save(actualCard);

    }


    public CreditCard save(CreditCard c){

        Assert.notNull(c);
        return creditCardRepository.save(c);
    }
}
