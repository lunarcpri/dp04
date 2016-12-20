package controllers.Recipe;

import controllers.AbstractController;
import domain.Category;
import domain.Recipe;
import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.RecipeService;

import java.awt.*;
import java.util.Collection;

@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(required=false) String query) {
        ModelAndView result;
        Collection<Recipe> recipeCollection;
        result = new ModelAndView("recipe/list");
        recipeCollection = recipeService.findAll();

        if (query!= null){
            recipeCollection = recipeService.findByKeyword(query);
        }
        result.addObject("recipes", recipeCollection);
        result.addObject("requestURI","recipe/list.do");
        return result;
    }


    @RequestMapping(value = "/{recipe}")
    public ModelAndView index(@PathVariable Recipe recipe) {
        ModelAndView result;
        Assert.notNull(recipe);
        result = new ModelAndView("recipe/index");

        result.addObject("recipe", recipe);
        result.addObject("requestURI","recipe/index.do");
        result.addObject("title",recipe.getTitle());
        return result;
    }
}
