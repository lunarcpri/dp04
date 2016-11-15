package services;

import domain.Contest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:spring/datasource.xml",
        "classpath*:spring/config/packages.xml"
})

@Transactional
public class ContestServiceTest extends AbstractTest {


    @Autowired
    private ContestService contestService;




    @Test
    public void testfindContestWithMoreRecipes(){
        Contest result = contestService.findContestWithMoreRecipes();
        Collection<Contest> contests = contestService.findAll();
        Contest aux = null;
        for(Contest e: contests){
            if (aux==null || aux.getRecipesQualified().size()<e.getRecipesQualified().size()){
                aux = e;
            }
        }
        Assert.isTrue(aux==result);
    }

    @Test
    public void testProcessWinner(){
        super.authenticate("admin");
        contestService.processWinner();
    }
}
