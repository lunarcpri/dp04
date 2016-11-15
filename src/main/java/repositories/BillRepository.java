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

    @Query("select b from Bill b where  b.paid_at is null and datediff(current_date, b.created_at)>30")
    Collection<Bill> getUnpaidBills();


    // DASHBOARD

    // - The average and the standard deviation of paid and unpaid monthly bills.
    @Query("select avg(s1.bills.size), avg(s2.bills.size), stddev(s1.bills.size), stddev(s2.bills.size) from Sponsor s1 join s1.bills b1 , Sponsor s2 join s2.bills b2 where b1.paid_at is null and b2.paid_at is not null")
    Object statiticsPaidUnpaidBills();

}
