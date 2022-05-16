package doanthuctap.repository;

import doanthuctap.entity.Working;
import doanthuctap.entity.WorkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkingRepository1 extends JpaRepository<Working, Integer> {




    List<Working> findAllByEmployeeId(int employeeId);
}
