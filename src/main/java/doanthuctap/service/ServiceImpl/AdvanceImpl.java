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

    @Override
    public Set<AdvanceDTO> listAdvance(Integer id) {
        Set<AdvanceDTO> advanceDTOSet = new HashSet<>();
        advanceRepository.findAllByEmployeeNo(id).forEach(advanceEntity -> {
            advanceDTOSet.add(advanceMapper.toDTO(advanceEntity));
        });
        return advanceDTOSet;
    }

    @Override
    public AdvanceDTO addAdvance(AdvanceDTO advanceDTO) {
        EmployeeEntity employeeEntity = employeeRepository.findById(advanceDTO.getEmployee_id()).orElseThrow(() -> new IllegalArgumentException("Employee is not found!"));
        AdvanceEntity entity = advanceMapper.toEntity(advanceDTO, employeeEntity);
        return advanceMapper.toDTO(advanceRepository.save(entity));
    }

    @Override
    public void deleteAdvance(Integer id) {
        AdvanceEntity entity = advanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Advance is not found!"));

        advanceRepository.delete(entity);

    }


}
