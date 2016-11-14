package repositories;

import domain.VideoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMaterialRepository extends JpaRepository<VideoMaterial, Integer> {
}
