package services;

import domain.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

    @Autowired
    ActorService actorService;

    @Test
    public void testActorService(){

        super.authenticate("user3");
        System.out.print(actorService.findActorByPrincipal());
    }
    @Test
    public void testActorService2(){

        String t = "12";
        String p = "domain.Nutritionist";
        try {
            System.out.print(Class.forName(t))
            ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
