package doanthuctap.repository;




import doanthuctap.entity.Advances;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AdvancesRepository1 extends JpaRepository<Advances, Integer> {


    @Query(value = "select a.* from advances1 a where employee_id=:id and a.date between :startDate and :endDate", nativeQuery = true)
    List<Advances> findAllByEmployeeNoAndMonth(@Param("id") Integer id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Advances> findAllByEmployeeId(int employeeId);

}
