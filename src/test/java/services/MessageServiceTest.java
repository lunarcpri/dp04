package services;

import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:spring/datasource.xml",
        "classpath*:spring/config/packages.xml"
})

@Transactional
public class MessageServiceTest extends AbstractTest{


    @Autowired
    private MessageService messageService;

    @Autowired
    private FolderService folderService;

    @Test
    public void newMessageTest() {
        super.authenticate("user1");
        int recipient = 23;
        User recipientUser = userService.findOne(recipient);
        List<Actor> recipients = new ArrayList<Actor>();
        recipients.add(recipientUser);
        Message m = new Message();
        m.setSubject("Prueba");
        m.setBody("Prueba body");
        m.setSender(userService.findByPrincipal());
        m.setPriority(Message.Priority.HIGH);
        m.setRecipients(recipients);
        Message savedMessage = messageService.newMessage(m);
        System.out.print(savedMessage.getFolders());
    }

    @Test
    public void testMoveMessage(){
        super.authenticate("user1");
        User u = userService.findByPrincipal();
        Folder folder = folderService.findInbox(u.getId());
        Folder folder1 = folderService.findFolderByMessageAndActor(u.getId(),154);
        System.out.println("Carpeta donde estalmente el mensaje: "+folder1.getMessages());
        System.out.println("Carpeta donde no estaba el mensaje: "+ folder.getMessages());
        messageService.moveMessage(154,folder.getId());
        System.out.println("Carpeta donde se mueve el mensaje: "+ folder.getMessages());
        System.out.println("Carpeta donde estaba el mensaje: "+ folder1.getMessages());
    }

    @Test
    public void testDeleteMessage(){
        super.authenticate("user1");
        User u = userService.findByPrincipal();
        int id = 153;
        Folder folder1 = folderService.findFolderByMessageAndActor(153,u.getId());
        System.out.println("Carpeta donde estaba  el mensaje antes de borrar "+ folder1.getFolderType());
        messageService.deleteById(id);
        System.out.println("Carpeta donde está actualmente el mensaje: "+ folder1.getFolderType());
    }

    @Test
    public void testIsMessageSpam(){
        super.authenticate("Kolter");
        int recipient = 14;
        User recipientUser = userService.findOne(recipient);
        List<Actor> recipients = new ArrayList<Actor>();
        recipients.add(recipientUser);
        Message m = new Message();
        m.setSubject("Prueba");
        m.setBody("culo");
        m.setSender(userService.findByPrincipal());
        m.setPriority(Message.Priority.HIGH);
        m.setRecipients(recipients);
        Message savedMessage = messageService.newMessage( m);
        for(Folder f : savedMessage.getFolders()){
            System.out.println(f.getFolderType());
        }
    }

}
