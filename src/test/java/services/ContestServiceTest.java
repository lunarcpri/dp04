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
    public void TestFindMinMaxAvgRecipesPerContest(){
        List<Object[]> list = contestService.findMinMaxAvgRecipesPerContest();
        Object[] result = list.get(0);
        Collection<Contest> contests = contestService.findAll();
        int max=0,min=1000000;
        for (Contest e:contests){
            if (e.getQualifiedRecipes().size()>max){
                max = e.getQualifiedRecipes().size();
            }
            if (e.getQualifiedRecipes().size()<min){
                min = e.getQualifiedRecipes().size();
            }
        }
        Assert.isTrue(min == result[0]);
        Assert.isTrue(max == result[1]);
    }

    @Test
    public void TestfindContestWithMoreRecipes(){
        Contest result = contestService.findContestWithMoreRecipes();
        Collection<Contest> contests = contestService.findAll();
        Contest aux = null;
        for(Contest e: contests){
            if (aux==null || aux.getQualifiedRecipes().size()<e.getQualifiedRecipes().size()){
                aux = e;
            }
        }
        Assert.isTrue(aux==result);
    }
}
