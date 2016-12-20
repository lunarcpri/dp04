package controllers.Sponsor;

import controllers.AbstractController;
import domain.Actor;
import domain.Category;
import domain.Message;
import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.SponsorService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/register")
    public ModelAndView index(@RequestParam(required=false)Category category,
                              @RequestParam(required=false)String search) {
        return  createEditModelAndView(new Sponsor(),null);
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST,params = "register")
    public ModelAndView create(
            @ModelAttribute("sponsor") @Valid Sponsor sponsor, BindingResult binding
    ) {
        ModelAndView result;
        if (binding.hasErrors()){
            result = createEditModelAndView(sponsor,"wrong");
        }else{
            try{
                Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
                sponsor.getUserAccount().setPassword(md5PasswordEncoder
                        .encodePassword(sponsor.getUserAccount().getPassword(),null));
                sponsorService.create(sponsor);
                UserDetails userDetails = userDetailsService.loadUserByUsername(sponsor.getUserAccount().getUsername());
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,sponsor.getUserAccount().getPassword(),
                                userDetails.getAuthorities());
                if(auth.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

                result = new ModelAndView("redirect:../");
            }catch (Throwable oops){
               result = createEditModelAndView(sponsor,"wrong");
            }

        }

        return result;
    }


    protected ModelAndView createEditModelAndView(Sponsor sponsor, String message) {
        ModelAndView result;

        result = new ModelAndView("sponsor/register");

        result.addObject("sponsor",sponsor);
        result.addObject("message",message);

        return result;
    }
}
