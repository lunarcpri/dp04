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
    // - The minimum, the average, and the maximum number of campaigns per sponsor.
    @Query("select min(s.campaigns.size), max (s.campaigns.size), avg (s.campaigns.size) from Sponsor s")
    Object statisticsPerSponsor();

    // - The minimum, the average, and the maximum number of active campaigns per sponsor.
    @Query("select min(s.campaigns.size),max(s.campaigns.size),avg(s.campaigns.size) from Sponsor s join s.campaigns c where CURRENT_DATE BETWEEN c.start_at and c.end_at")
    Object statisticsActivePerSponsor();



}
