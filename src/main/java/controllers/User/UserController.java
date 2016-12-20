package controllers.User;

import controllers.AbstractController;
import domain.Category;
import domain.Nutritionist;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repositories.UserRepository;
import security.Authority;
import services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(required=false) String query) {
        ModelAndView result;
        Collection<User> userCollection;
        result = new ModelAndView("user/list");
        userCollection = userService.findAll();

        if (query!= null){
            userCollection = userService.findByKeyword(query);
        }

        result.addObject("users", userCollection);
        result.addObject("requestURI","user/list.do");
        return result;
    }


    @RequestMapping(value = "/{user}")
    public ModelAndView index(@PathVariable User user) {
        ModelAndView result;
        Assert.notNull(user);
        result = new ModelAndView("user/index");

        result.addObject("user", user);
        result.addObject("recipes",user.getRecipes());
        result.addObject("requestURI","user/index.do");
        return result;
    }

    @RequestMapping(value = "/register")
    public ModelAndView index() {
        return create(new User(),null);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,params = "register")
    public ModelAndView create(
            @ModelAttribute("user") @Valid User user, BindingResult binding
    ) {
        ModelAndView result;

        if (binding.hasErrors()){
            result = createEditModelAndView(user,"wrong");
        }else{
            try{
                Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
                user.getUserAccount().setPassword(md5PasswordEncoder
                        .encodePassword(user.getUserAccount().getPassword(),null));
                userService.create(user);
                UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserAccount().getUsername());
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,user.getUserAccount().getPassword(),
                                userDetails.getAuthorities());
                if(auth.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

                result = new ModelAndView("redirect:../");
            }catch (Throwable oops){
                result = createEditModelAndView(user,"wrong");
            }

        }

        return result;
    }

    protected ModelAndView createEditModelAndView(User user, String message) {
        ModelAndView result;

        result = new ModelAndView("user/register");

        result.addObject("user",user);
        result.addObject("message",message);

        return result;
    }
}
