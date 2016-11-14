package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.RecipeRepository;
import repositories.UserRepository;
import security.Authority;
import security.UserAccountService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private UserOrNutritionistService userOrNutritionistService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private LikesService likesService;

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

    private void save(Recipe recipe){
        Assert.notNull(recipe);

        recipeRepository.save(recipe);
    }

    public Recipe newRecipe(String ticker, String title,
                            String picture,
                            String summary,
                            String hits,
                            Collection<Quantity> quantities,
                            Collection<Step> steps,
                            User author,
                            Category category){

        userAccountService.assertRole("USER");
        Date createdAt = new Date();
        Recipe recipe = create();

        recipe.setCreated_at(createdAt);
        recipe.setTicker(ticker);
        recipe.setTitle(title);
        recipe.setSummary(summary);
        recipe.setHits(hits);
        Assert.notNull(quantities);
        recipe.setQuantities(quantities);
        Assert.notNull(steps);
        recipe.setSteps(steps);
        Assert.notNull(author);
        recipe.setAuthor(author);
        Assert.notNull(category);
        recipe.setCategory(category);

        save(recipe);
        return recipe;
    }

    public Collection<Recipe> findUserRecipes(){
        Collection<Recipe> recipe;

        recipe = findAllRecipesByUser(actorService.findByPrincipal().getId());

        return recipe;

    }

    public void modifyRecipe(Recipe recipe, String title,
                             String picture,
                             String summary,
                             String hits,
                             Collection<Quantity> quantities,
                             Collection<Step> steps,
                             User author,
                             Category category){

        Assert.isTrue(recipe.getId() == actorService.findByPrincipal().getId());
        userAccountService.assertRole("USER");
        Recipe modified = recipe;

        Date updatedAt = new Date();

        modified.setUpdated_at(updatedAt);
        modified.setTitle(title);
        modified.setPicture(picture);
        modified.setSummary(summary);
        modified.setHits(hits);
        Assert.notNull(quantities);
        modified.setQuantities(quantities);
        Assert.notNull(steps);
        modified.setSteps(steps);
        Assert.notNull(author);
        modified.setAuthor(author);
        Assert.notNull(category);
        modified.setCategory(category);

        save(modified);
    }

    public void delete(Recipe recipe){
        Assert.notNull(recipe);
        userAccountService.assertRole("USER");
        recipeRepository.delete(recipe.getId());
    }


    // like parameter indicates if is a like or dislike: If it is true is like and if it is false is dislike


    public void addLike(int id, boolean like){
        Recipe recipe = findOne(id);
        Actor actor = actorService.findByPrincipal();
        UserOrNutritionist userOrNutritionist = userOrNutritionistService.findUserOrNutritionistByActor(actor);
        Assert.isTrue(!isActorAuthoredRecipe(actor.getId(),id));
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
        Actor actor = actorService.findOne(actorid);
        return recipe.getAuthor() == actor;
    }

    public List<Object[]> findStdevAvgStepsPerRecipe(){
        List<Object[]> result;

        result = recipeRepository.findStdevAvgStepsPerRecipe();
        Assert.notNull(result);

        return result;
    }

}
