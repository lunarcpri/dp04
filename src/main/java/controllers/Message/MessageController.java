/* WelcomeController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.Message;

import controllers.AbstractController;
import domain.Actor;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public MessageController() {
		super();
	}

	@Autowired
	private MessageService messageService;

	@Autowired
	private ActorService actorService;

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/new")
	public ModelAndView index() {
		ModelAndView result;
		Collection<Actor> actorCollection;

		return createEditModelAndView(new Message(),null);
	}

	@RequestMapping(value = "/new",method = RequestMethod.POST,params = "send")
	public  ModelAndView newMessage(@Valid @ModelAttribute("newMessage") Message message, BindingResult binding) {
        ModelAndView result;
        Collection<Actor> actorCollection;
        result = new ModelAndView("message/new");
        actorCollection = actorService.findAll();
        result.addObject("actors", actorCollection);
	    if (binding.hasErrors()){
           return createEditModelAndView(message,"message.commit.error");
        }
        try{
            messageService.newMessage(message);

            return  new ModelAndView("redirect:/message/new.do");
        }catch (Throwable oops){
            result.addObject("newMessage",message);
            result.addObject("message","message.commit.error");
            return result;
        }
	}

    protected ModelAndView createEditModelAndView(Message message2, String message) {
        ModelAndView result;
        Collection<Actor> actorCollection;

        actorCollection = actorService.findAll();
        result = new ModelAndView("message/new");
        result.addObject("newMessage", message2);
        result.addObject("actors",  actorCollection);
        result.addObject("message", message);

        return result;
    }



}