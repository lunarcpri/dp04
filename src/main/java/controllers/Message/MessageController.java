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

import com.sun.org.apache.xpath.internal.operations.Mod;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.FolderService;
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

	@Autowired
    private FolderService folderService;

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/new")
	public ModelAndView index() {
		ModelAndView result;
		Collection<Actor> actorCollection;

		return createNewModelAndView(new Message(),null);
	}

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(required=false) Integer folderId) {
        ModelAndView result;
        Collection<Folder> foldersCollection;
        Collection<Message> messageCollection;
        Actor principal;

        principal = actorService.findActorByPrincipal();
        foldersCollection = principal.getFolders();
        Folder folder;

        if (folderId != null){
            folder = folderService.findOne(folderId);
            Assert.isTrue(folder.getActor()== principal);
            messageCollection = folder.getMessages();
        }else{
            folder = folderService.findInbox(principal.getId());
            messageCollection = folder.getMessages();
        }
        foldersCollection = actorService.findActorByPrincipal().getFolders();
        result = new ModelAndView("message/list");
        result.addObject("messageList",messageCollection);
        result.addObject("folders",foldersCollection);
        result.addObject("folder",folder);
        result.addObject("requestURI","message/list.do");

        return result;
    }

	@RequestMapping(value = "/new",method = RequestMethod.POST,params = "send")
	public  ModelAndView newMessage(@Valid @ModelAttribute("newMessage") Message message, BindingResult binding) {
        ModelAndView result;
        Collection<Actor> actorCollection;
        result = new ModelAndView("message/new");
        actorCollection = actorService.findAll();
        result.addObject("actors", actorCollection);
	    if (binding.hasErrors()){
           return createNewModelAndView(message,"message.commit.error");
        }
        try{
            messageService.newMessage(message);

            return  new ModelAndView("redirect:/message/list.do");
        }catch (Throwable oops){
            result.addObject("newMessage",message);
            result.addObject("message","message.commit.error");
            return result;
        }
	}

    protected ModelAndView createNewModelAndView(Message message2, String message) {
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