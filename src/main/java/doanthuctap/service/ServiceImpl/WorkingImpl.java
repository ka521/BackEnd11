package doanthuctap.service.ServiceImpl;

import doanthuctap.entity.Working;
import doanthuctap.repository.EmployeeRepository;
import doanthuctap.repository.WorkingRepository;
import doanthuctap.repository.WorkingRepository1;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import doanthuctap.dto.WorkingDTO;
import doanthuctap.entity.EmployeeEntity;
import doanthuctap.entity.WorkingEntity;
import doanthuctap.mapper.WorkingMapper;

import doanthuctap.service.WorkingService;

import java.util.HashSet;
import java.util.Set;

@Service
public class WorkingImpl implements WorkingService {
    @Autowired
    private WorkingMapper workingMapper;
    @Autowired
    private WorkingRepository workingRepository;
    @Autowired
    private WorkingRepository1 workingRepository1;
    @Autowired
    private EmployeeRepository employeeRepository;





    @Override
    public void deleteWorking(Integer id) {
        Working entity1 = workingRepository1.findById(id).orElseThrow(() -> new IllegalArgumentException("Working is not found!"));

        workingRepository1.delete(entity1);

    }


}
