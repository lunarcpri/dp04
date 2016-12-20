package repositories;

import domain.Actor;
import domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import security.UserAccount;

import java.util.Collection;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{

    @Query("select a from Actor a where a.userAccount = ?1 ")
    Actor findActorByUserAccount(UserAccount userAccount);
}
