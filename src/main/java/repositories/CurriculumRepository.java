package repositories;

import domain.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {


    @Query("select n.curriculum from Nutritionist n where n.id=?1")
    Curriculum findCurriculumByActor(int id);
}
