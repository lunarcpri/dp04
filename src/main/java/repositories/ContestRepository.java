package repositories;

import domain.Contest;
import domain.QualifiedRecipe;
import domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer> {

    @Query("select q.recipe from Contest c join c.qualifiedRecipes q where q.winner=true")
    Collection<Recipe> findWinnersByContestId(int contestId);

    @Query("select c from Contest c join c.qualifiedRecipes q where c.closed_at < CURRENT_DATE")
    Collection<Contest> findClosedContests();

    @Query("select min(c.qualifiedRecipes.size)as s1 ,max(c.qualifiedRecipes.size)as s2, avg(c.qualifiedRecipes.size) as s3 from Contest c")
    List<Object[]> findMinMaxAvgRecipesPerContest();

    @Query("select c from Contest c where c.qualifiedRecipes.size = (select max(c2.qualifiedRecipes.size) from Contest c2)")
    Contest findContestWithMoreRecipes();
}
