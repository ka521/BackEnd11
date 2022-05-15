package doanthuctap.service.ServiceImpl;

import doanthuctap.repository.AdvanceRepository;
import doanthuctap.repository.EmployeeRepository;
import doanthuctap.repository.WorkingRepository;
import doanthuctap.response.StatisticResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import doanthuctap.entity.AdvanceEntity;
import doanthuctap.entity.WorkingEntity;
import doanthuctap.service.StatisticService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class StatisticImpl implements StatisticService {

    @Autowired
    private WorkingRepository workingRepository;
    @Autowired
    private AdvanceRepository advanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public StatisticResponse getStatistic(Integer id) {
        double moneyPerHour = employeeRepository.findMoneyPerHourByNo(id);

        LocalDate start = YearMonth.now().atDay(1);
        LocalDate end = YearMonth.now().atEndOfMonth();

        List<WorkingEntity> workingList = workingRepository.findAllByEmployeeNoAndMonth(id, start, end);
        List<AdvanceEntity> advanceList = advanceRepository.findAllByEmployeeNoAndMonth(id, start, end);


        StatisticResponse statisticResponse = new StatisticResponse();
        double totalWorking = workingList.stream().mapToDouble(working -> (working.getHour() * moneyPerHour)).sum();
        double totalAdvance = advanceList.stream().mapToDouble(AdvanceEntity::getMoney).sum();

        statisticResponse.setNumberOfWorkingDay(workingList.size());
        statisticResponse.setTotalGet(totalWorking);
        statisticResponse.setTotalAdvances(totalAdvance);
        statisticResponse.setSummary(totalWorking - totalAdvance);
        return statisticResponse;
    }

    private LocalDate firstDayOfPreviousMonth(LocalDate date) {
        return date.minusMonths(1).withDayOfMonth(1);
    }


    private LocalDate lastDayOfPreviousMonth(LocalDate date) {
        return date.withDayOfMonth(1).minusDays(1);
    }
}
