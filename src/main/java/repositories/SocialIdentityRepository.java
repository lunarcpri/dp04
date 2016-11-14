package repositories;

import domain.SocialIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer> {
}
