package repositories;

import domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("select b from Bill b where  b.sponsor.id = MONTH(?2) and MONTH(b.created_at) = ?1")
    Collection<Bill> getMonthlyBills(int sponsorID, Date date);

}
