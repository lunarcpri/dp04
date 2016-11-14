package services;

import domain.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"
})
@Transactional
public class RecipeServiceTest extends AbstractTest {


    @Autowired
    private RecipeService recipeService;

    @Test
    public void testFindStdevAvgStepsPerRecipe(){
        List<Object[]> list = recipeService.findStdevAvgStepsPerRecipe();
        Object[] arr = list.get(0);
        Collection<Recipe> recipes = recipeService.findAll();
        double[] datos;

        System.out.println(arr[1]);
    }
}
