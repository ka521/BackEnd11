package doanthuctap.service.ServiceImpl;

import doanthuctap.entity.Working;
import doanthuctap.repository.EmployeeRepository;
import doanthuctap.repository.WorkingRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doanthuctap.service.WorkingService;

@Service
public class WorkingImpl implements WorkingService {


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
