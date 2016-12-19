package controllers.Category;

import domain.Category;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CategoryService;

import java.util.Collection;

@Controller
@RequestMapping("/nutritionist")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Category> categoriesCollection;
        result = new ModelAndView("category/list");
        categoriesCollection = categoryService.findAll();

        result.addObject("recipes", categoriesCollection);
        result.addObject("requestURI","category/list.do");
        return result;
    }
}
