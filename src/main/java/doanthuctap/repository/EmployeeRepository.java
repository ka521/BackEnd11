package doanthuctap.repository;


import doanthuctap.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findByFullName(String fullName);

    @Query(value = "SELECT * from employee  where team_id=?1", nativeQuery = true)
    List<Employee> findEmployeeInTeam(int id);




    @Query(value = "select e.money_per_hour from employee e where e.no=:id", nativeQuery = true)
    double findMoney(@Param("id") Integer id);


}
