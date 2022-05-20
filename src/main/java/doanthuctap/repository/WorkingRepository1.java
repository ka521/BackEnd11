package doanthuctap.repository;

import doanthuctap.entity.Working;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkingRepository1 extends JpaRepository<Working, Integer> {




    @Query(value = "select w.* from working1 w where employee_id=:id and w.date between :startDate and :endDate", nativeQuery = true)
    List<Working> findAllByEmployeeNoAndMonth(@Param("id") Integer id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    List<Working> findAllByEmployeeId(int employeeId);
}
