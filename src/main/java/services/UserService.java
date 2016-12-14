package services;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ActorService actorService;

    @Autowired
    private FolderService folderService;

    public UserService(){
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public User create(){
        User result;

        result = new User();

        return result;
    }

    public User findOne(int userId){
        User result;

        result = userRepository.findOne(userId);
        Assert.notNull(result);

        return result;
    }


    public Collection<User> findAll()
    {
        Collection<User> result;

        result = userRepository.findAll();
        Assert.notNull(result);

        return  result;
    }
    
    public void save(User user){
        Assert.notNull(user);
        
        userRepository.save(user);
    }

    public void delete(User user) {
        Assert.notNull(user);
        Assert.isTrue(user.getId() != 0);

       userRepository.delete(user);
    }


    /* Other methdos */

    public User edit(User user) {
        User result;

        result =  findByPrincipal();
        Assert.notNull(result);
        result.setName(user.getName());
        result.setSurnames(user.getSurnames());
        result.setAddress(user.getAddress());
        result.setEmail(user.getEmail());
        result.setPhone(user.getPhone());
        result.setSocialIdentities(user.getSocialIdentities());
        save(result);

        return result;
    }

    public List<Object[]> findMinMaxAvgRecipesPerUser(){
        List<Object[]> result;

        result = userRepository.findMinMaxAvgRecipesPerUser();
        Assert.notNull(result);

        return result;
    }

    public User findUserWithMoreRecipes(){
        User result;

        result = userRepository.findUserWithMoreRecipes();
        Assert.notNull(result);

        return result;
    }

    public List<User> findAllByPopularity(){
        return userRepository.findAllByPopularity();
    }

    public List<User> findAllByLikes(){
        return userRepository.findAllByLikes();
    }

    public User findByPrincipal() {
        User result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = userRepository.findByUserAccountId(userAccount.getId());

        return result;
    }


    Collection<User> findByKeyword(String key){
        Collection<User> result;

        result = userRepository.findByKeyword(key);
        Assert.notNull(result);

        return result;
    }

    public void create(User u){
        Assert.notNull(u);
        List<Authority> authorities = new ArrayList<Authority>();
        Authority a = new Authority();
        a.setAuthority("USER");
        authorities.add(a);
        u.getUserAccount().setAuthorities(authorities);
        u = userRepository.save(u);
        Assert.notNull(u);
        folderService.createDefaultFolders(u);
    }
}
