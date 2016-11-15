package repositories;

import domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("select b from Bill b where  b.sponsor.id = ?1 and MONTH(b.created_at) = MONTH(?2) and YEAR(b.created_at) = YEAR(current_date)")
    Collection<Bill> getMonthlyBills(int sponsorID, Date date);

}
