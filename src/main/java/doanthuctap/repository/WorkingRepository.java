package doanthuctap.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import doanthuctap.entity.WorkingEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WorkingRepository extends CrudRepository<WorkingEntity, Integer> {
    Iterable<WorkingEntity> findAllByEmployeeNo(Integer employee_id);

    @Query(value = "select w.* from working w where employee_id=:id and w.date between :startDate and :endDate", nativeQuery = true)
    List<WorkingEntity> findAllByEmployeeNoAndMonth(@Param("id") Integer id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    boolean existsByEmployeeNoAndDate(int employee_id, Date date);
}
