package controllers.Sponsor;

import controllers.AbstractController;
import domain.Category;
import domain.Sponsor;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.SponsorService;

import javax.validation.Valid;

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
        ModelAndView result;

        result = new ModelAndView("sponsor/register");

        result.addObject("sponsor",new Sponsor());

        return result;
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST,params = "register")
    public ModelAndView create(
            @Valid Sponsor sponsor, BindingResult binding
    ) {
        ModelAndView result;
        if (binding.hasErrors()){
            result = new ModelAndView("sponsor/register");
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
                System.out.println(oops.getMessage());
                result = new ModelAndView("sponsor/register");
                result.addObject("message","wrong");
            }

        }

        return result;
    }
}
