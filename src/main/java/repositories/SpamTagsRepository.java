package repositories;

import domain.SpamTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpamTagsRepository extends JpaRepository<SpamTags, Integer> {
}
