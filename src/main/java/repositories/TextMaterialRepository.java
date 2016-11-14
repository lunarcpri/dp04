package repositories;

import domain.TextMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextMaterialRepository extends JpaRepository<TextMaterial, Integer> {
}
