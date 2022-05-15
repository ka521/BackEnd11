package doanthuctap.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StatisticResponse {
    private int numberOfWorkingDay;
    private double totalGet;
    private double totalAdvances;
    private double summary;

}
