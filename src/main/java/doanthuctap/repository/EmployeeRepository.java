package doanthuctap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import doanthuctap.entity.EmployeeEntity;
import doanthuctap.entity.TeamEntity;

import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

    @Query(value = "select *,b.name from Employee e , Team b  ", nativeQuery = true)
    List<EmployeeEntity> findAll();


    Set<EmployeeEntity> findAllByTeam(TeamEntity team);



    @Query(value = "select e.money_per_hour from Employee e where e.no=:id", nativeQuery = true)
    double findMoney(@Param("id") Integer id);
}
