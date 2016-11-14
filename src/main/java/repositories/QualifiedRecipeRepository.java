package repositories;

import domain.Banner;
import domain.Contest;
import domain.QualifiedRecipe;
import domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface QualifiedRecipeRepository extends JpaRepository<QualifiedRecipe, Integer> {

    @Query("select distinct qr.contest from QualifiedRecipe qr join qr.contest c where qr.winner=true and c.id=?1")
    Collection<Contest> findWinnersRecipesByContestId(int id);

    @Query("select qr.recipe from QualifiedRecipe qr join qr.contest c where  c.id=?1")
    Collection<Recipe> findRecipesByContestId(int id);

    @Query("select qr from QualifiedRecipe qr join qr.contest c where qr.contest.id=?1")
    Collection<QualifiedRecipe> findQualifiedRecipesByContest(int id);
}
