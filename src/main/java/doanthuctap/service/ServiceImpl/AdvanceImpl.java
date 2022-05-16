package doanthuctap.service.ServiceImpl;

import doanthuctap.repository.AdvanceRepository;
import doanthuctap.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import doanthuctap.dto.AdvanceDTO;
import doanthuctap.entity.AdvanceEntity;
import doanthuctap.entity.EmployeeEntity;
import doanthuctap.mapper.AdvanceMapper;

import doanthuctap.service.AdvanceService;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdvanceImpl implements AdvanceService {
    @Autowired
    private AdvanceMapper advanceMapper;
    @Autowired
    private AdvanceRepository advanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;




}
