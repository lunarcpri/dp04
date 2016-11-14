package services;

import domain.Contest;
import domain.QualifiedRecipe;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.QualifiedRecipeRepository;
import repositories.RecipeRepository;

import java.util.Collection;

@Transactional
@Service
public class QualifiedRecipeService {

    @Autowired
    private QualifiedRecipeRepository qualifiedRecipeRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public QualifiedRecipeService(){

        super();
    }

    private void save(QualifiedRecipe qualifiedRecipe){
        Assert.notNull(qualifiedRecipe);

        qualifiedRecipeRepository.save(qualifiedRecipe);
    }

    public Collection<QualifiedRecipe> findQualifiedRecipesByContest(int id){
        return qualifiedRecipeRepository.findQualifiedRecipesByContest(id);
    }

    public Collection<Recipe> findWinnersRecipesByContestId(int id){
        Collection<Recipe> result;

        result = qualifiedRecipeRepository.findWinnersByContestId(id);
        Assert.notNull(result);

        return result;
    }

    public Collection<Recipe> findRecipesByContestId(int id){
        Collection<Recipe> result;

        result = qualifiedRecipeRepository.findRecipesByContestId(id);
        Assert.notNull(result);

        return result;
    }


    public void qualifieRecipe(Recipe recipe, Contest contest){

        QualifiedRecipe qualifiedRecipe = new QualifiedRecipe();

        Assert.isTrue(recipeRepository.findRecipeLikes(recipe.getId()).size() >= 5 && recipeRepository.findRecipeDislikes(recipe.getId()).size()== 0);
        qualifiedRecipe.setContest(contest);
        qualifiedRecipe.setRecipe(recipe);

        save(qualifiedRecipe);
    }

}
