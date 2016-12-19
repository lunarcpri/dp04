package controllers.Nutritionist;

import controllers.AbstractController;
import domain.Nutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.NutritionistService;

import javax.validation.Valid;

@Controller
@RequestMapping("/nutritionist")
public class NutritionistController extends AbstractController {

    @Autowired
    private NutritionistService nutritionistService;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/register")
    public ModelAndView index() {
        ModelAndView result;

        result = new ModelAndView("nutritionist/register");

        result.addObject("nutritionist",new Nutritionist());

        return result;
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST,params = "register")
    public ModelAndView create(
            @Valid Nutritionist nutritionist, BindingResult binding
    ) {
        ModelAndView result;

        if (binding.hasErrors()){
            result = new ModelAndView("nutritionist/register");
        }else{
            try{
                Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
                nutritionist.getUserAccount().setPassword(md5PasswordEncoder
                        .encodePassword(nutritionist.getUserAccount().getPassword(),null));
                nutritionistService.create(nutritionist);
                UserDetails userDetails = userDetailsService.loadUserByUsername(nutritionist.getUserAccount().getUsername());
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,nutritionist.getUserAccount().getPassword(),
                                userDetails.getAuthorities());
                if(auth.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

                result = new ModelAndView("redirect:../");
            }catch (Throwable oops){
                result = new ModelAndView("nutritionist/register");
                result.addObject("message","wrong");
            }

        }

        return result;
    }
}
