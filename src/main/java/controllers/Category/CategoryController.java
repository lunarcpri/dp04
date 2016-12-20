package controllers.Category;

import domain.Category;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CategoryService;

import java.util.Collection;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Category> categoriesCollection;
        result = new ModelAndView("category/list");
        categoriesCollection = categoryService.findAll();

        result.addObject("categories", categoriesCollection);
        result.addObject("requestURI","category/list.do");
        return result;
    }

    @RequestMapping(value = "/{category}")
    public ModelAndView index(@PathVariable Category category) {
        ModelAndView result;
        Assert.notNull(category);
        result = new ModelAndView("category/index");

        result.addObject("category", category);
        result.addObject("recipes",category.getRecipes());
        result.addObject("requestURI","category/index.do");
        return result;
    }
}
