package repositories;

import domain.Recipe;
import domain.UserOrNutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserOrNutritionistRepository extends JpaRepository<UserOrNutritionist, Integer> {

    @Query("select u.following from UserOrNutritionist u join u.following f where u.id= ?1 and f.id=?2 ")
    Collection<UserOrNutritionist> following(int idPrincipal, int idFollower);


}
