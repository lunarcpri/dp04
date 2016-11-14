package services;

import domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.BillRepository;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    public SponsorService sponsorService;

    public BillService(){
       super();
    }

    public Collection<Bill> list(){

        return billRepository.getMonthlyBills(sponsorService.findByPrincipal().getId(), new Date());
    }

    public Collection<Bill> list(Date date){

        return billRepository.getMonthlyBills(sponsorService.findByPrincipal().getId(), date);
    }

    public void paid(Collection<Bill> bills){

        Assert.notNull(bills);
        for(Bill bill:bills){

            bill.setPaid_at(new Date());
        }
    }




}
