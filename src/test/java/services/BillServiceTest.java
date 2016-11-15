package services;

import domain.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:spring/datasource.xml",
        "classpath*:spring/config/packages.xml"
})
@Transactional
public class BillServiceTest extends AbstractTest {

    @Autowired
    private BillService billService;

    @Test
    public void testGetMonthlyBills(){

        super.authenticate("user3");

        Collection<Bill> bills = billService.list();

        Assert.notEmpty(bills);
        for (Bill bill: bills){

            Assert.isTrue(bill.getSponsor().equals(billService.sponsorService.findByPrincipal()));
            Assert.isTrue(bill.getCreated_at().equals(new Date()));

        }

    }

    @Test
    public void testGetMonthlyBillsFromDate(){

        super.authenticate("user3");

        Date date = new Date(2016);

        Collection<Bill> bills = billService.list();

        for (Bill bill: bills){

            Assert.isTrue(bill.getSponsor().equals(billService.sponsorService.findByPrincipal()));

        }

    }
}
