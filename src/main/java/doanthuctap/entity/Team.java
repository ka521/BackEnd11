package doanthuctap.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "team")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "employeeTeam", fetch = FetchType.LAZY)
//    @JsonIgnore
    private List<EmployeeModel> employee = new ArrayList<>();
//    private Set<EmployeeModel> employees =new HashSet<>();
}
