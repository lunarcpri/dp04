package repositories;


import domain.MasterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterClassRepository extends JpaRepository<MasterClass, Integer> {
}
