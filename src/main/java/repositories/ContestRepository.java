package repositories;

import domain.Contest;
import domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer> {

    @Query("select c from Contest c join c.recipesQualified q where c.closed_at < CURRENT_DATE")
    Collection<Contest> findClosedContests();

    @Query("select min(c.recipesQualified.size)as s1 ,max(c.recipesQualified.size)as s2, avg(c.recipesQualified.size) as s3 from Contest c")
    List<Object[]> findMinMaxAvgRecipesPerContest();

    @Query("select c from Contest c where c.recipesQualified.size = (select max(c2.recipesQualified.size) from Contest c2)")
    Contest findContestWithMoreRecipes();
}
