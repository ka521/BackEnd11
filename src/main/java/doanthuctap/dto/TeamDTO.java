package doanthuctap.dto;



import doanthuctap.entity.EmployeeModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDTO {
        private int teamId;
        private String name;
        private List<EmployeeModel> employee;
}
