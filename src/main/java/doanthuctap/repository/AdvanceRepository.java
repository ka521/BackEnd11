package doanthuctap.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import doanthuctap.entity.AdvanceEntity;

import java.time.LocalDate;
import java.util.List;

public interface AdvanceRepository extends CrudRepository<AdvanceEntity, Integer> {


    @Query(value = "select a.* from advance a where employee_id=:id and a.date between :startDate and :endDate", nativeQuery = true)
    List<AdvanceEntity> findAllByEmployeeNoAndMonth(@Param("id") Integer id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
