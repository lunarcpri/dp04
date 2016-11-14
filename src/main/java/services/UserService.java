package services;

import domain.Recipe;
import domain.SocialIdentity;
import domain.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private SocialIdentityService socialIdentityService;

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

    public User findByPrincipal() {
        User result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public User findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        User result;

        result = userRepository.findByUserAccountId(userAccount.getId());


        return result;
    }

    /* Other methdos */

    public User edit(String name, String surnames, String address, String email, String phone) {
        User result;

        result = findByPrincipal();
        Assert.notNull(result);
        result.setName(name);
        result.setSurnames(surnames);
        result.setAddress(address);
        result.setEmail(email);
        result.setPhone(phone);
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
}