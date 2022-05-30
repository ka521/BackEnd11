package doanthuctap.service.ServiceImpl;


import doanthuctap.dto.EmployeeDTO;
import doanthuctap.entity.Advances;
import doanthuctap.entity.EmployeeModel;
import doanthuctap.entity.Team;
import doanthuctap.entity.Working;
import doanthuctap.mapper.Mapper;
import doanthuctap.repository.AdvancesRepository1;
import doanthuctap.repository.EmployeeRepository;
import doanthuctap.repository.TeamRepository;
import doanthuctap.repository.WorkingRepository1;
import doanthuctap.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private WorkingRepository1 workingRepository1;

    @Autowired
    private AdvancesRepository1 advanceRepository1;
    @Override
    public EmployeeModel saveEmployee(EmployeeModel employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeModel> employeeList = employeeRepository.findAll();
        System.out.println(employeeList);
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeList.stream().forEach(employee -> {

            employeeDTOList.add(mapper.toEmployeeDTOList(employee));
        });
        return employeeDTOList;
    }

    @Override
    public EmployeeModel updateEmployee(EmployeeModel employee, int id) {



        EmployeeModel existingEmployee = employeeRepository.getById(id);
        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setAddress(employee.getAddress());

        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setStartDay(employee.getStartDay());
        existingEmployee.setMoneyPerHour(employee.getMoneyPerHour());
        existingEmployee.setTotalHours(employee.getTotalHours());
        existingEmployee.setImageURL(employee.getImageURL());
        existingEmployee.setEmployeeTeam(employee.getEmployeeTeam());



        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }


    @Override
    public EmployeeDTO findEmployee(Integer id) {
        EmployeeModel entity = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee_id: " + id + " is not found!"));
        return mapper.toEmployeeDTO(entity);

    }

    @Override
    public void deleteEmployee(Integer id) {
        EmployeeModel entity = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee_id: " + id + " is not found!"));

        List<Working> workingEntitySet = workingRepository1.findAllByEmployeeId(id);
        List<Advances> advanceEntitySet = advanceRepository1.findAllByEmployeeId(id);
        if (workingEntitySet != null && advanceEntitySet != null) {
            workingEntitySet.forEach(workingEntity -> {

                workingRepository1.delete(workingEntity);
            });
            advanceEntitySet.forEach(advanceEntity -> {

                advanceRepository1.delete(advanceEntity);
            });

            employeeRepository.delete(entity);

        } else {
            throw new IllegalArgumentException("Delete is not complete!");
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        EmployeeModel employee = employeeRepository.getById(id);
        EmployeeDTO employeeDTO = mapper.toEmployeeDTO(employee);
        return employeeDTO;
    }

}
