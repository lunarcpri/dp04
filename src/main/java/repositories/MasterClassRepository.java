package repositories;


import domain.MasterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterClassRepository extends JpaRepository<MasterClass, Integer> {

    @Query("select min(c.masterClasses.size),max(c.masterClasses.size), avg(c.masterClasses.size), stddev(c.masterClasses.size) from Cook c")
    List<Object[]> findMinMaxAvgStddevMasterClassesPerCook();

    @Query("select count(m) from MasterClass m where promoted=true")
    Integer numberOfPromotedMasterClasses();

    @Query("select avg(l.size) from MasterClass m join m.learningMaterials l group by l.class")
    List<Double> AvgLearningMaterialsPerMasterClassGroupByLearningMaterialKind();

    @Query("select 1.0*(select count(m1) from Cook c1 join c1.masterClasses m1 where promoted=1)/count(m) " +
            "as avgpromoted, 1.0*(select count(m2) from Cook c2 join c2.masterClasses m2 where promoted=0 )/count(m) " +
            "as avgdemoted from Cook c join c.masterClasses m")
    Double[] findAvgPromotedDemotedMasterClasses();
}