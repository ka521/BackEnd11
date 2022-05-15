package doanthuctap.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import doanthuctap.dto.AdvanceDTO;
import doanthuctap.entity.AdvanceEntity;
import doanthuctap.entity.EmployeeEntity;

@Component
public class AdvanceMapper {
    @Autowired
    private ModelMapper mapper;

    public AdvanceDTO toDTO(AdvanceEntity entity) {
        AdvanceDTO advanceDTO = new AdvanceDTO();
        advanceDTO.setId(entity.getId());
        advanceDTO.setDate(entity.getDate());
        advanceDTO.setMoney(entity.getMoney());
        advanceDTO.setEmployee_id(entity.getEmployee().getNo());
        return advanceDTO;
    }

    public AdvanceEntity toEntity(AdvanceDTO dto, EmployeeEntity employeeEntity) {
        AdvanceEntity advanceEntity = new AdvanceEntity();
        advanceEntity.setId(dto.getId());
        advanceEntity.setDate(dto.getDate());
        advanceEntity.setMoney(dto.getMoney());
        advanceEntity.setEmployee(employeeEntity);
        return advanceEntity;
    }
}
