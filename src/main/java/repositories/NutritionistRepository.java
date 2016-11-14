package repositories;

import domain.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer> {

    @Query("select c from Nutritionist c where c.userAccount.id=?1")
    Nutritionist findByNutritionistAccountId(int userAccountId);
}
