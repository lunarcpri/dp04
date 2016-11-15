package services;

import domain.Recipe;
import domain.Step;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

import java.util.ArrayList;
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

    @Autowired
    private UserService userService;

    @Test
    public void testNewRecipe(){
        super.authenticate("user1");

        Recipe recipe = new Recipe();
        recipe.setTitle("Nueva Receta");
        recipe.setAuthor(userService.findByPrincipal());
        recipe.setPicture("http://google.es");
        recipe.setHits("Esto es un hint");
        Recipe recipe2 = recipeService.newRecipe(recipe);

        Assert.assertNotNull(recipe2);
    }

    @Test
    public void testFindByKeyword(){
        super.authenticate("user1");

        Step  step1 = new Step();
        step1.setPicture("http://google.es");
        step1.setDescription("Description");
        step1.setHints("Hints");
        List<Step> stepCollect = new ArrayList<Step>();
        stepCollect.add(step1);
        Recipe recipe = new Recipe();
        recipe.setSummary("Summary");
        recipe.setTitle("Nueva Receta");
        recipe.setAuthor(userService.findByPrincipal());
        recipe.setPicture("http://google.es");
        recipe.setHits("Esto es un hint");
        recipe.setSteps(stepCollect);
        Recipe recipe2 = recipeService.newRecipe(recipe);
        List<Recipe> list = new ArrayList<Recipe>(recipeService.findByKeyword("Nueva"));
        org.springframework.util.Assert.isTrue(list.get(0).getTitle().equals("Nueva Receta"));
    }
}
