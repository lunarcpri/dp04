package controllers;

import domain.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/")
public class BasicController {

    @RequestMapping(value = "/register")
    public ModelAndView index() {
        ModelAndView result;

        result = new ModelAndView("welcome/register");

        return result;
    }

}
