package doanthuctap.service.ServiceImpl;

import doanthuctap.entity.Advances;
import doanthuctap.entity.Working;
import doanthuctap.repository.AdvancesRepository1;
import doanthuctap.repository.EmployeeRepository;
import doanthuctap.repository.WorkingRepository1;
import doanthuctap.response.StatisticResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import doanthuctap.service.StatisticService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class StatisticImpl implements StatisticService {

    @Autowired
    private WorkingRepository1 workingRepository;
    @Autowired
    private AdvancesRepository1 advanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public StatisticResponse getStatistic(Integer id) {
        double moneyPerHour = employeeRepository.findMoney(id);

        LocalDate start = YearMonth.now().atDay(1);
        LocalDate end = YearMonth.now().atEndOfMonth();

        List<Working> workingList = workingRepository.findAllByEmployeeNoAndMonth(id, start, end);
        List<Advances> advanceList = advanceRepository.findAllByEmployeeNoAndMonth(id, start, end);


        StatisticResponse statisticResponse = new StatisticResponse();
        double totalWorking = workingList.stream().mapToDouble(working -> (working.getHour() * moneyPerHour)).sum();
        double totalAdvance = advanceList.stream().mapToDouble(Advances::getMoney).sum();

        statisticResponse.setNumberOfWorkingDay(workingList.size());
        statisticResponse.setTotalGet(totalWorking);
        statisticResponse.setTotalAdvances(totalAdvance);
        statisticResponse.setSummary(totalWorking - totalAdvance);
        return statisticResponse;
    }

}
