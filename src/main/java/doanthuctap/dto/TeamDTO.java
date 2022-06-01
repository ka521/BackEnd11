package doanthuctap.dto;



import doanthuctap.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDTO {
        private int teamId;
        private String name;
        private List<Employee> employee;
}
