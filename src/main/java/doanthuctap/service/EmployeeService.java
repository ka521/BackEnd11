package doanthuctap.service;


import doanthuctap.dto.EmployeeDTO;
import doanthuctap.entity.EmployeeModel;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
    EmployeeModel saveEmployee(EmployeeModel employee);

    List<EmployeeDTO> getAllEmployees();

    EmployeeModel updateEmployee(EmployeeModel employee, int id);

    void deleteEmployee(Integer id);
    EmployeeDTO findEmployee(Integer id);


    EmployeeDTO getEmployeeById(int id);


}
