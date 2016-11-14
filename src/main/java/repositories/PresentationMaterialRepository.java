package repositories;

import domain.PresentationMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationMaterialRepository extends JpaRepository<PresentationMaterial, Integer> {
}
