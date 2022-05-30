package doanthuctap.dto;

import doanthuctap.entity.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EmployeeDTO {
    private int no;

    private String fullName;

    private Integer age;

    private String gender;

    private String address;

    private String phoneNumber;

    private Date startDay;

    private Integer moneyPerHour;

    private Integer totalHours;

    private String imageURL;

    private Team teamInfo;

    private String teamName;

    private int teamId;

}
