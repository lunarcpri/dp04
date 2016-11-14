package repositories;


import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select u from User u where u.name like '%?1%' or u.surnames like '%?1%'")
    Collection<User> findByKeyword(String key);

    @Query("select min(u.recipes.size) AS s, max(u.recipes.size) AS s2 , avg(u.recipes.size) as s3 from User u")
    List<Object[]> findMinMaxAvgRecipesPerUser();

    @Query("select u from User u where u.recipes.size = (select max(u2.recipes.size) from User u2)")
    User findUserWithMoreRecipes();

    @Query("select u from User u order by u.follower.size DESC")
    List<User> findAllByPopularity();


    @Query("select u, (select avg(l.size) from User u2 join u2.recipes r join r.likes l where u2.id=u.id) as averagel " +
            "from User u order by averagel DESC")
    List<User> findAllByLikes();
}
