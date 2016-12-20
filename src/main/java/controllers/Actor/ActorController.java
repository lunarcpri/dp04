package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import domain.Nutritionist;
import domain.SocialIdentity;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

    @Autowired
    private ActorService actorService;


    @RequestMapping(value = "/edit")
    public ModelAndView index(@RequestParam(required=false,defaultValue = "personal") String edit) {
        ModelAndView result;
        Actor actor;
        result = new ModelAndView("actor/edit");
        actor = actorService.findActorByPrincipal();
        result.addObject("actor",actor);
        result.addObject("edit",edit);
        result.addObject("role",actor.getClass().getName());

        return result;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,params = "save")
    public ModelAndView edit(
            @Valid @ModelAttribute("role") Actor actor, BindingResult binding
    ) {
        Actor principal = actorService.findActorByPrincipal();
        ModelAndView result;
        result = index("personal");
        if (binding.hasErrors()){
            System.out.print("binding error");
        }else{
            try{
                actor.setUserAccount(principal.getUserAccount());
                for(SocialIdentity s: actor.getSocialIdentities()){
                    if (s.getActor()== null){
                        s.setActor(actor);
                    }
                }
                actorService.save(actor);
                System.out.println("No error");
            }catch (Throwable oops){
                result.addObject("message","wrong");
            }

        }

        return result;
    }



}
