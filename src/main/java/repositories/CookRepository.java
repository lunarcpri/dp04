package repositories;

import domain.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CookRepository extends JpaRepository<Cook, Integer> {

    @Query("select c from Cook c where c.userAccount.id=?1")
    Cook findByCookAccountId(int userAccountId);
}
