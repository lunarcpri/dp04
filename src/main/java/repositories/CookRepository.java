package repositories;

import domain.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookRepository extends JpaRepository<Cook, Integer> {

    @Query("select c from Cook c where c.userAccount.id=?1")
    Cook findByCookAccountId(int userAccountId);


    @Query("select c2,(select count(mm) from Cook c join c.masterClasses mm where mm.promoted = true and c.id = c2.id)" +
            " as pp  from Cook c2 order by pp DESC")
    List<Object[]> findCooksOrderByPromotedMasterClasses();
}
