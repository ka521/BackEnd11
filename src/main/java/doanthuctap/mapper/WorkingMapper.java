package doanthuctap.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import doanthuctap.dto.WorkingDTO;
import doanthuctap.entity.EmployeeEntity;
import doanthuctap.entity.WorkingEntity;

@Component
public class WorkingMapper {
    @Autowired
    private ModelMapper mapper;


    public WorkingDTO toDTO(WorkingEntity entity) {
        WorkingDTO workingDTO = new WorkingDTO();
        workingDTO.setId(entity.getId());
        workingDTO.setDate(entity.getDate());
        workingDTO.setHour(entity.getHour());
        workingDTO.setEmployee_id(entity.getEmployee().getNo());
        return workingDTO;
    }

    public WorkingEntity toEntity(WorkingDTO dto, EmployeeEntity employeeEntity) {
        WorkingEntity workingEntity = new WorkingEntity();
        workingEntity.setId(dto.getId());
        workingEntity.setDate(dto.getDate());
        workingEntity.setHour(dto.getHour());
        workingEntity.setEmployee(employeeEntity);
        return workingEntity;
    }
}
