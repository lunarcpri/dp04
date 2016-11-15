package services;

import domain.User;
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
        "classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"
})
@Transactional
public class UserServiceTest extends AbstractTest {


    @Autowired
    private UserService userService;

    @Test
    public void testFindMinMaxAvgRecipesPerUser(){
        List<Object[]> list = userService.findMinMaxAvgRecipesPerUser();
        Object[] result = list.get(0);
        Collection<User> users = userService.findAll();
        double avg=0;
        int max=0,min=1000000;
        for (User e:users){
            if (e.getRecipes().size()>max){
                max = e.getRecipes().size();
            }
            if (e.getRecipes().size()<min){
                min = e.getRecipes().size();
            }
            avg+=e.getRecipes().size();
        }
        avg = avg / users.size();
        System.out.println(result[0]);
        Assert.isTrue(min == result[0]);
        Assert.isTrue(max == result[1]);
    }

    @Test
    public void testFindUserWithMoreRecipes(){
        User result = userService.findUserWithMoreRecipes();
        Collection<User> users = userService.findAll();
        User aux = null;
        for(User e: users){
            if (aux==null || aux.getRecipes().size()<e.getRecipes().size()){
                aux = e;
            }
        }
        Assert.isTrue(aux==result);
    }
}
