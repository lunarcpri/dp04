package controllers.Contest;

import controllers.AbstractController;
import domain.Contest;
import domain.Recipe;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ContestService;
import services.RecipeService;

import java.util.Collection;

@Controller
@RequestMapping("/contest")
public class ContestController extends AbstractController {

    @Autowired
    private ContestService contestService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Contest> contestCollection;
        result = new ModelAndView("contest/list");
        contestCollection = contestService.findAll();

        result.addObject("contests", contestCollection);
        result.addObject("requestURI","contest/list.do");
        return result;
    }

    @RequestMapping(value = "/{contest}")
    public ModelAndView index(@PathVariable Contest contest) {
        ModelAndView result;
        Assert.notNull(contest);
        result = new ModelAndView("contest/index");

        result.addObject("contest", contest);
        result.addObject("recipesWinner",contest.getWinnerRecipes());
        result.addObject("recipesQualified",contest.getRecipesQualified());
        result.addObject("requestURI","contest/index.do");
        return result;
    }
}
