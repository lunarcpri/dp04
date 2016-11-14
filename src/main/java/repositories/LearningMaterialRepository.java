package repositories;

import domain.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer> {
}
