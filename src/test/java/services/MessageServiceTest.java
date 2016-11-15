package services;

import domain.Message;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

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
        int recipient = 14;
        User recipientUser = userService.findOne(recipient);
        //folderService.createDefaultFolders(recipientUser);
       // folderService.createDefaultFolders(userService.findByPrincipal());
        Message m = new Message();
        m.setSubject("Prueba");
        m.setBody("Prueba body");
        m.setSender(userService.findByPrincipal());
        m.setPriority(Message.Priority.HIGH);
        messageService.newMessage(recipient, m);
    }

}
