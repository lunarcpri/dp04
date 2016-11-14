package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.UserOrNutritionistRepository;
import security.UserAccountService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserOrNutritionistService {

    @Autowired
    private UserOrNutritionistRepository userOrNutritionistRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;


    public UserOrNutritionistService(){

        super();

    }

    //Simple CRUD Methods


    public Collection<UserOrNutritionist> findAll() {
        Collection<UserOrNutritionist> result;

        result = userOrNutritionistRepository.findAll();
        Assert.notNull(result);

        return result;
    }

    public UserOrNutritionist findOne(int userOrNutritionistId) {
        Assert.isTrue(userOrNutritionistId != 0);

        UserOrNutritionist result;

        result = userOrNutritionistRepository.findOne(userOrNutritionistId);
        Assert.notNull(result);

        return result;
    }

    public void save(UserOrNutritionist userOrNutritionist) {
        Assert.notNull(userOrNutritionist);

        userOrNutritionistRepository.save(userOrNutritionist);
    }

    public void delete(UserOrNutritionist userOrNutritionist) {
        Assert.notNull(userOrNutritionist);
        Assert.isTrue(userOrNutritionist.getId() != 0);
        Assert.isTrue(userOrNutritionistRepository.exists(userOrNutritionist.getId()));

        userOrNutritionistRepository.delete(userOrNutritionist);
    }

    public Boolean isFollowing(int id){

        Assert.isTrue(!userOrNutritionistRepository.following(userService.findByPrincipal().getId(), id).isEmpty());

        return true;
    }


    public void follow(UserOrNutritionist userOrNutritionist){

        Assert.notNull(userOrNutritionist);
        Assert.isTrue(!isFollowing(userOrNutritionist.getId()));
        userAccountService.assertRole("USER,NUTRITIONIST");

        Collection<UserOrNutritionist> followers = userService.findOne(userOrNutritionist.getId()).getFollower();
        Collection<UserOrNutritionist> following = userService.findByPrincipal().getFollowing();

        followers.add(userService.findByPrincipal());
        following.add(userService.findOne(userOrNutritionist.getId()));

        userService.findByPrincipal().setFollowing(following);
        userService.findOne(userOrNutritionist.getId()).setFollower(followers);

        Assert.isTrue(!isFollowing(userOrNutritionist.getId()));

        save(userOrNutritionist);
        save(userService.findByPrincipal());

    }

    public void unfollow(UserOrNutritionist userOrNutritionist){

        Assert.notNull(userOrNutritionist);
        Assert.isTrue(isFollowing(userOrNutritionist.getId()));
        userAccountService.assertRole("USER,NUTRITIONIST");

        Collection<UserOrNutritionist> followers = userService.findOne(userOrNutritionist.getId()).getFollower();
        Collection<UserOrNutritionist> following = userService.findByPrincipal().getFollowing();

        followers.remove(userService.findByPrincipal());
        following.remove(userService.findOne(userOrNutritionist.getId()));

        userService.findByPrincipal().setFollowing(following);
        userService.findOne(userOrNutritionist.getId()).setFollower(followers);

        save(userOrNutritionist);
        save(userService.findByPrincipal());

    }

    public UserOrNutritionist findUserOrNutritionistByActor(Actor actor){
        UserOrNutritionist result;

        result = findOne(actor.getId());
        Assert.notNull(result);

        return result;
    }

    public Collection<Recipe> findAllRecipesByFollowingActors(){
        UserOrNutritionist userOrNutritionist = findUserOrNutritionistByActor(userService.findByPrincipal());
        Collection<UserOrNutritionist> followers= userOrNutritionist.getFollowing();
        List<Recipe> list = new ArrayList<Recipe>();
        for(UserOrNutritionist e: followers){
            User userAux = (User) e;
            list.addAll(userAux.getRecipes());
        }
        Assert.notNull(list);
        return list;
    }
}
