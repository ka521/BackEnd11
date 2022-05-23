package doanthuctap.mapper;

import org.springframework.stereotype.Component;
import doanthuctap.dto.EmployeeDTO;
import doanthuctap.entity.EmployeeEntity;
import doanthuctap.entity.TeamEntity;

@Component
public class EmployeeMapper {


    public EmployeeDTO toDTO(EmployeeEntity entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setNo(entity.getNo());
        dto.setFullName(entity.getFullName());
        dto.setAge(entity.getAge());
       dto.setSex(entity.getSex());
        dto.setAddress(entity.getAddress());
        dto.setMoneyPerHour(entity.getMoneyPerHour());
        dto.setPhone(entity.getPhone());
        dto.setStartDay(entity.getStartDay());
        dto.setTeamID(entity.getTeam().getNo());
        dto.setName(entity.getTeam().getName());
        return dto;
    }

    public EmployeeEntity toEntity(EmployeeDTO dto, TeamEntity team) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setNo(dto.getNo());
        entity.setFullName(dto.getFullName());
        entity.setAge(dto.getAge());
        entity.setSex(dto.getSex());
        entity.setAddress(dto.getAddress());
        entity.setMoneyPerHour(dto.getMoneyPerHour());
        entity.setPhone(dto.getPhone());
        entity.setStartDay(dto.getStartDay());
        entity.setTeam(team);
        return entity;
    }


}
