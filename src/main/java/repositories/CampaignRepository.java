package repositories;


import domain.Bill;
import domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{

    // DASHBOARD
    // - The minimum, the average, and the maximum number of campaigns per sponsor.  V
    @Query("select min(s.campaigns.size), max (s.campaigns.size), avg (s.campaigns.size) FROM Sponsor s")
    Collection<Bill> statisticsPerSponsor();

    // - The ranking of companies according the number of campaigns that they?ve organised via their sponsors.
    @Query("select min(s.campaigns.size),max(s.campaigns.size),avg(s.campaigns.size) FROM Sponsor s JOIN s.campaigns c WHERE CURRENT_DATE BETWEEN c.start_at and c.end_at")
    Collection<Bill> statisticsActivePerSponsor();

}
