package doanthuctap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class EmployeeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "start_day")
    private Date startDay;
    @Column(name = "money_per_hour")
    private Integer moneyPerHour;
    @Column(name = "total_hours")
    private Integer totalHours;
    @Column(name = "image_URL")
    private String imageURL;
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team employeeTeam;

//    private Set<WorkingModel> workings =new HashSet<>();

//    private Set<Advances> advances =new HashSet<>();
//    private List<WorkingModel> workings = new ArrayList<>();

// mapping onetomany


}
