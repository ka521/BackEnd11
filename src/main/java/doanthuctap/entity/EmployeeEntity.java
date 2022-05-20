package doanthuctap.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;
    @Column(name = "money_per_hour")
    private double moneyPerHour;
    @Column(name = "phone")
    private String phone;


    @Column(name = "start_day")
    @Temporal(TemporalType.DATE)
    private Date startDay;



    @Column(name = "sex")
    private String Sex;

    @Lob
    private byte[] image;
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private TeamEntity team;


}
