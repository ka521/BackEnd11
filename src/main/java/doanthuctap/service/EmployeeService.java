package doanthuctap.service;


import doanthuctap.dto.EmployeeDTO;
import doanthuctap.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<EmployeeDTO> getAllEmployees();

    Employee updateEmployee(Employee employee, int id);

    void deleteEmployee(Integer id);
    EmployeeDTO findEmployee(Integer id);


    EmployeeDTO getEmployeeById(int id);


}
