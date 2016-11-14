package repositories;

import domain.DomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainEntityRepository extends JpaRepository<DomainEntity, Integer> {
}
