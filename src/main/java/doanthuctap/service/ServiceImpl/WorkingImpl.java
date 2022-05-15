package doanthuctap.service.ServiceImpl;

import doanthuctap.repository.EmployeeRepository;
import doanthuctap.repository.WorkingRepository;
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
    private EmployeeRepository employeeRepository;


    @Override
    public Set<WorkingDTO> listWorking(Integer id) {
        Set<WorkingDTO> workingDTOSet = new HashSet<>();
        workingRepository.findAllByEmployeeNo(id).forEach(workingEntity -> {
            workingDTOSet.add(workingMapper.toDTO(workingEntity));
        });
        return workingDTOSet;
    }

    @SneakyThrows
    @Override
    public WorkingDTO addWorking(WorkingDTO workingDTO) {
        if (workingRepository.existsByEmployeeNoAndDate(workingDTO.getEmployee_id(), workingDTO.getDate()))
            throw new Exception("Working Date is existing!");

        EmployeeEntity employeeEntity = employeeRepository.findById(workingDTO.getEmployee_id()).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        WorkingEntity entity = workingMapper.toEntity(workingDTO, employeeEntity);
        return workingMapper.toDTO(workingRepository.save(entity));
    }

    @Override
    public void deleteWorking(Integer id) {
        WorkingEntity entity = workingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Working is not found!"));

        workingRepository.delete(entity);

    }


}
