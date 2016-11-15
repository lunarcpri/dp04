package services;

import domain.Actor;
import domain.Likes;
import domain.Recipe;
import domain.UserOrNutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.RecipeRepository;
import security.UserAccountService;

import java.util.*;

@Service
@Transactional
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserOrNutritionistService userOrNutritionistService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private LikesService likesService;

    @Autowired
    CategoryService categoryService;

    public RecipeService(){

        super();
    }

    Collection<Recipe> findAllRecipesByUser(int userId)
    {

        return recipeRepository.findRecipesByUser(userId);
    }


    Collection<Recipe> findAll()
    {

        return recipeRepository.findAll();
    }

    public Recipe findOne(int id){
        Recipe result;

        result = recipeRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    public Recipe create(){
        Recipe result;

        result = new Recipe();

        return result;
    }

    private Recipe save(Recipe recipe){
        Assert.notNull(recipe);

        return recipeRepository.save(recipe);
    }

    public Recipe newRecipe(Recipe recipe){

        userAccountService.assertRole("USER");
        Date createdAt = new Date();
        recipe.setUpdated_at(createdAt);
        recipe.setCreated_at(createdAt);
        recipe.setTicker(generateTicker());
        recipe.setAuthor(userService.findByPrincipal());

        return save(recipe);
    }

    private String generateTicker(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String result=calendar.get(Calendar.YEAR) % 100+""+calendar.get(Calendar.MONTH)+""+calendar.get(Calendar.DAY_OF_MONTH)+"-";
        Date date = new Date();
        for(int i=0;i<4;i++){
            Random r = new Random();
            char c = (char)(r.nextInt(26) + 'a');
            result=result+String.valueOf(c);
        }
        return result;

    }

    public Collection<Recipe> findUserRecipes(){
        Collection<Recipe> recipe;

        recipe = findAllRecipesByUser(userService.findByPrincipal().getId());

        return recipe;

    }

    public Collection<Recipe> findByKeyword(String key){
        Collection<Recipe> result;

        result = recipeRepository.findByKeyword(key);
        Assert.notNull(result);

        return result;
    }

    public void modifyRecipe(Recipe recipe){
        userAccountService.assertRole("USER");
        Recipe old = findOne(recipe.getId());
        Assert.isTrue(!old.isRead_only());
        Date updatedAt = new Date();

        old.setUpdated_at(updatedAt);
        old.setTitle(recipe.getTitle());
        old.setPicture(recipe.getPicture());
        old.setSummary(recipe.getSummary());
        old.setHits(recipe.getHits());
        old.setUpdated_at(new Date());
        Assert.notNull(recipe.getQuantities());
        old.setQuantities(recipe.getQuantities());
        Assert.notNull(recipe.getSteps());
        old.setSteps(recipe.getSteps());
        Assert.notNull(recipe.getCategory());
        old.setCategory(recipe.getCategory());

        save(old);
    }

    public void delete(Recipe recipe){
        Assert.notNull(recipe);
        userAccountService.assertRole("USER");
        recipeRepository.delete(recipe.getId());
    }


    // like parameter indicates if is a like or dislike: If it is true is like and if it is false is dislike


    public void addLike(int id, boolean like){
        Recipe recipe = findOne(id);
        UserOrNutritionist userOrNutritionist = userService.findByPrincipal();
        List<Likes> likes = new ArrayList<Likes>(likesService.findRecipeLikeByActor(userOrNutritionist.getId(),id));
        if (likes.size()>0){
            Likes likeRecipe = likes.get(0);
            if (likeRecipe.getIsLike() == like){
                likesService.delete(likeRecipe);
            }else{
                likeRecipe.setIsLike(like);
                likesService.save(likeRecipe);
            }
        }else{
            likesService.like(recipe,userOrNutritionist,like);
        }
    }



    public boolean isActorAuthoredRecipe(int actorid, int recipeid){
        Recipe recipe = findOne(recipeid);
        Actor actor = userOrNutritionistService.findOne(actorid);
        return recipe.getAuthor() == actor;
    }

    public List<Object[]> findStdevAvgStepsPerRecipe(){
        List<Object[]> result;

        result = recipeRepository.findStdevAvgStepsPerRecipe();
        Assert.notNull(result);

        return result;
    }

    public Collection<Recipe> findAllByCategory(int id){
        Collection<Recipe> result;

        result = recipeRepository.findAllByCategory(id);
        Assert.notNull(result);

        return  result;
    }

}
