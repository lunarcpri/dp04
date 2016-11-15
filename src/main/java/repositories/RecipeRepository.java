package repositories;

import domain.Likes;
import domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query("select distinct u.recipes from User u where u.id = ?1")
    Collection<Recipe> findRecipesByUser(int userId);

    @Query("select r from Recipe r where r.ticker like %?1% or r.title like %?1% or r.summary like %?1%")
    Collection<Recipe> findByKeyword(String key);

    @Query("select r.likes from Recipe r join r.likes l where l.isLike=true and r.id= ?1")
    Collection<Likes> findRecipeLikes(int id);

    @Query("select r.likes from Recipe r join r.likes l where l.isLike=false and r.id= ?1")
    Collection<Likes> findRecipeDislikes(int id);

    @Query("select stddev(r.steps.size) as pepe,avg(r.steps.size) from Recipe r")
    List<Object[]> findStdevAvgStepsPerRecipe();

    @Query("select stddev(r.quantities.size), avg(r.quantities.size) from Recipe r")
    List<Object[]> findStdevAvgIngredientsPerRecipe();

    @Query("select r from Recipe r where r.category.id=?1")
    Collection<Recipe> findAllByCategory(int id);



}
