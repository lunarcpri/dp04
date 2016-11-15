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

    @Query("select c from Contest c where c.closed_at < CURRENT_DATE and c.ended=false")
    Collection<Contest> findClosedContests();

    @Query("select min(c.recipesQualified.size)as s1 ,max(c.recipesQualified.size)as s2, avg(c.recipesQualified.size) as s3 from Contest c")
    List<Object[]> findMinMaxAvgRecipesPerContest();

    @Query("select c from Contest c where c.recipesQualified.size = (select max(c2.recipesQualified.size) from Contest c2)")
    Contest findContestWithMoreRecipes();

    @Query("select r,(select distinct l1.size from Recipe r1 join r1.likes l1 where r1.id=r.id and isLike=true) as likes, " +
            "(select distinct  l2.size from Recipe r2 join r2.likes l2 where r2.id=r.id and isLike=false) as dislikes  " +
            " from Contest c join c.recipesQualified r join r.likes l where c.id=?1 order by likes asc, dislikes desc")
    Collection<Object[]> findContestRecipesOrderByLikes(int id);
}
