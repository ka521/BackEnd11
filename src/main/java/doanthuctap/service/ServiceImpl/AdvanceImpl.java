package doanthuctap.service.ServiceImpl;

import doanthuctap.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import doanthuctap.service.AdvanceService;

@Service
public class AdvanceImpl implements AdvanceService {

    @Autowired
    private EmployeeRepository employeeRepository;




}
