package services;

import domain.Folder;
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
public class FolderServiceTest  extends AbstractTest{


    @Autowired
    private FolderService folderService;

    @Test
    public void testCreateDefaultFolders() {
        super.authenticate("user1");
        User u = userService.findByPrincipal();
        folderService.createDefaultFolders(u);
        System.out.print(folderService.findAll());
    }

    @Test
    public void createCustomFolder(){
        super.authenticate("user1");
        String name = "Normal Box";
        Folder f = new Folder();
        f.setName(name);
        f.setActor(userService.findByPrincipal());
        folderService.createCustomFolder(f);
        System.out.println(new ArrayList<Folder>(folderService.findAll()).get(0).getName());

    }
}
