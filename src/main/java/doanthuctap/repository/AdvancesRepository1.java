package doanthuctap.repository;



import doanthuctap.entity.AdvanceEntity;
import doanthuctap.entity.Advances;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdvancesRepository1 extends JpaRepository<Advances, Integer> {



    List<Advances> findAllByEmployeeId(int employeeId);

}
