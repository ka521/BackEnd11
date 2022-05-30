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


    //Phân trang
    //SELECT * FROM `employee`
    //LIMIT 5
    //OFFSET 1
//        @Query("SELECT u FROM User u WHERE u.def = :def")
//        public List<EmployeeModel> findUserByDefQuery(@Param("def") Integer def);

//    public interface UserRepository extends JpaRepository<User, Long> {
//
//        // Khi được gắn @Query, thì tên của method không còn tác dụng nữa
//        // Đây là JPQL
//        @Query("select u from User u where u.emailAddress = ?1")
//        User myCustomQuery(String emailAddress);
//
//        // Đây là Native SQL
//        @Query(value = "select * from User u where u.email_address = ?1", nativeQuery = true)
//        User myCustomQuery2(String emailAddress);
//    }

    //        List<User> findAllByAtk(int atk);
//
//        List<User> findAllByAgiBetween(int start, int end);
//
//        @Query("SELECT u FROM User u WHERE u.def = :def")
//        User findUserByDefQuery(@Param("def") Integer def);
//
//        List<User> findAllByAgiGreaterThan(int agiThreshold);
}
