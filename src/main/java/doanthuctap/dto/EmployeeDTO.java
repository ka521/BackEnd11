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
    @NotEmpty(message = "Full name is not empty")
    @Size(min = 1, max = 255)
    private String fullName;
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 70, message = "Age should not be greater than 70")
    private int age;
    private boolean isMale;
    @Size(min = 0, max = 255)
    private String address;
    @Positive(message = "Money Per Hour must be greater than 0")
    private double moneyPerHour;
    @NotEmpty(message = "Phone number is not empty")
    @Pattern(regexp = "\\d{10}", message = "Phone number is invalid!")
    private String phone;

//    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDay;

    private int teamID;
    private  String sex;

}
