package repositories;

import domain.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

    @Query("select a from Sponsor a where a.userAccount.id=?1")
    Sponsor findBySponsorAccountId(int userAccountId);
}
