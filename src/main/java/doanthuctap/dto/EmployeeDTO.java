package doanthuctap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class EmployeeDTO {
    private int no;

    private String fullName;

    private int age;
    private boolean isMale;
    @Size(min = 0, max = 255)
    private String address;

    private double moneyPerHour;

    private String phone;

//    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDay;

    private int teamID;
    private  String sex;
    private String name;
}
