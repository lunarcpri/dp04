package repositories;

import domain.Bill;
import domain.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

    @Query("select a from Sponsor a where a.userAccount.id=?1")
    Sponsor findBySponsorAccountId(int userAccountId);

    // DASHBOARD

    // - The ranking of companies according their monthly bills.
    @Query("select s from Sponsor s order by s.campaigns.size DESC")
    Collection<Bill> statisticsRankingByCampaignsNumber();

    // - The average and the standard deviation of paid and unpaid monthly bills.
    @Query("select s.company From Sponsor s order by s.bills.size DESC")
    Collection<Bill> statisticsRankingByMonthlyBills();

    // - The sponsors who have not managed a campaign for the last three months.
    @Query("select  s from Sponsor s join s.campaigns c where datediff(CURRENT_DATE, c.end_at)>90")
    Collection<Bill> statisticsWithoutActivity();

    // - The companies that have spent less than the average in their campaigns.
    @Query("select s.company from  Sponsor s join s.company c where  c.cost < (select avg(b.cost) from Bill b)")
    Collection<Bill> statisticsLessSpent();

    // - The companies that have spent at least 90% the maximum amount of money that a company has spent on a campaign.
    @Query("select s.company from Sponsor s join s.bills b where b.cost > 0.90*(select max(b.cost) from Sponsor s join s.bills b)")
    Collection<Bill> statisticsMoreSpent();


}
