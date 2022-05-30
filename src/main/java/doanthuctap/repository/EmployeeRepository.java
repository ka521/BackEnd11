package doanthuctap.repository;


import doanthuctap.entity.EmployeeModel;
import doanthuctap.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> {

    public List<EmployeeModel> findByFullName(String fullName);

    @Query(value = "SELECT * from employee  where team_id=?1", nativeQuery = true)
    List<EmployeeModel> findEmployeeInTeam(int id);




    @Query(value = "select e.money_per_hour from employee e where e.no=:id", nativeQuery = true)
    double findMoney(@Param("id") Integer id);


}
