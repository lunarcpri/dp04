package repositories;

import domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{


    @Query("select a from Actor u where u.getUserAccount.id=?1")
    Actor findByActorAccountId(int userAccountId);
}
