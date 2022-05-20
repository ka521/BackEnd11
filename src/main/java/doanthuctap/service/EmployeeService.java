package doanthuctap.service;

import org.springframework.data.domain.Page;
import doanthuctap.dto.EmployeeDTO;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Set<EmployeeDTO> listEmployee();

    EmployeeDTO newEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployee(Integer id);

    void deleteEmployee(Integer id);

    String deleteAll(List<Integer> ids);



    EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO);

    Set<EmployeeDTO> listEmployeeByTeam(Integer teamID);


}
