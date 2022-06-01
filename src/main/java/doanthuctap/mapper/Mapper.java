package doanthuctap.mapper;


import doanthuctap.dto.EmployeeDTO;
import doanthuctap.dto.TeamDTO;
import doanthuctap.entity.Employee;
import doanthuctap.entity.Team;
import org.springframework.stereotype.Component;


@Component
public class Mapper {

    public Employee toEmployeeEntity(EmployeeDTO dto) {
        Employee entity = new Employee();
        entity.setNo(dto.getNo());
        entity.setFullName(dto.getFullName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setStartDay(dto.getStartDay());
        entity.setMoneyPerHour(dto.getMoneyPerHour());
        entity.setTotalHours(dto.getTotalHours());
        entity.setImageURL(dto.getImageURL());
        return entity;
    }

    public EmployeeDTO toEmployeeDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();

        dto.setNo(entity.getNo());
        dto.setFullName(entity.getFullName());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setStartDay(entity.getStartDay());
        dto.setMoneyPerHour(entity.getMoneyPerHour());
        dto.setTotalHours(entity.getTotalHours());
        dto.setImageURL(entity.getImageURL());
        dto.setTeamInfo(entity.getEmployeeTeam());
        dto.setTeamName(entity.getEmployeeTeam().getName());
        dto.setTeamId(entity.getEmployeeTeam().getTeamId());

        return dto;
    }

    public EmployeeDTO toEmployeeDTOList(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
//        if (entity.getEmployeeId() != 0) {
//            dto.setEmployeeId(entity.getEmployeeId());
//        }
        dto.setNo(entity.getNo());
        dto.setFullName(entity.getFullName());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setStartDay(entity.getStartDay());
        dto.setMoneyPerHour(entity.getMoneyPerHour());
        dto.setTotalHours(entity.getTotalHours());
        dto.setImageURL(entity.getImageURL());
//        dto.setTeamInfo(entity.getEmployeeTeam());
        dto.setTeamName(entity.getEmployeeTeam().getName());

        return dto;
    }

    public Team toTeamEntity(TeamDTO dto) {
        Team entity = new Team();
        entity.setTeamId(dto.getTeamId());
        entity.setName(dto.getName());
        entity.setEmployee(dto.getEmployee());

        return entity;
    }

    public TeamDTO toTeamDTO(Team entity) {
        TeamDTO dto = new TeamDTO();
        if (entity.getTeamId() != 0) {
            dto.setTeamId(entity.getTeamId());
        }
        dto.setTeamId(entity.getTeamId());
        dto.setName(entity.getName());
        dto.setEmployee(entity.getEmployee());

        return dto;
    }
//
//        public NewEntity toEntity(NewDTO dto, NewEntity entity) {
//            entity.setTitle(dto.getTitle());
//            entity.setContent(dto.getContent());
//            entity.setShortDescription(dto.getShortDescription());
//            entity.setThumbnail(dto.getThumbnail());
//            return entity;
//        }
}
