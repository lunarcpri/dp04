package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.BillRepository;

@Service
@Transactional
public class BillService {


    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository){
       this.billRepository = billRepository;
    }
}
