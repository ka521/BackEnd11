package doanthuctap.service.ServiceImpl;

import doanthuctap.repository.AdvanceRepository;
import doanthuctap.repository.EmployeeRepository;
import doanthuctap.repository.TeamRepository;
import doanthuctap.repository.WorkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import doanthuctap.dto.EmployeeDTO;
import doanthuctap.entity.AdvanceEntity;
import doanthuctap.entity.EmployeeEntity;
import doanthuctap.entity.TeamEntity;
import doanthuctap.entity.WorkingEntity;

import doanthuctap.mapper.EmployeeMapper;

import doanthuctap.service.EmployeeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRep;
    @Autowired
    private EmployeeMapper mapper;
    @Autowired
    private TeamRepository teamRep;


    @Autowired
    private WorkingRepository workingRepository;

    @Autowired
    private AdvanceRepository advanceRepository;

    @Override
    public Set<EmployeeDTO> listEmployee() {
        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
        employeeRep.findAll().forEach(employee -> {
            employeeDTOSet.add(mapper.toDTO(employee));
        });

        return employeeDTOSet;
    }

    @Override
    public Set<EmployeeDTO> listEmployeeByTeam(Integer teamID) {
        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
        TeamEntity entity = teamRep.findById(teamID).orElseThrow(() -> new IllegalArgumentException("Team_id: " + teamID + " is not found!"));


        employeeRep.findAllByTeam(entity).forEach(employeeEntity -> {
            employeeDTOSet.add(mapper.toDTO(employeeEntity));
        });
        return employeeDTOSet;

    }

    @Override
    public Page<EmployeeDTO> getPage(Integer pageIndex) {
        Page<EmployeeEntity> page = employeeRep.findAllWithPageIndex(PageRequest.of(pageIndex, 5));
        return page.map(entity -> mapper.toDTO(entity));
    }

    @Override
    public EmployeeDTO findEmployee(Integer id) {
        EmployeeEntity entity = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee_id: " + id + " is not found!"));
        return mapper.toDTO(entity);

    }

    @Override
    public Set<EmployeeDTO> findByName(String name) {
        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
        Set<EmployeeEntity> employeeEntitySet = employeeRep.findByName(name);
        employeeEntitySet.forEach(employeeEntity -> {
            employeeDTOSet.add(mapper.toDTO(employeeEntity));
        });
        return employeeDTOSet;
    }

    @Override
    public EmployeeDTO newEmployee(EmployeeDTO employeeDTO) {
        TeamEntity teamEntity = teamRep.findById(employeeDTO.getTeamID()).orElseThrow(() -> new IllegalArgumentException("Team is not found!"));
        EmployeeEntity entity = employeeRep.save(mapper.toEntity(employeeDTO, teamEntity));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteEmployee(Integer id) {
        EmployeeEntity entity = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee_id: " + id + " is not found!"));

        Iterable<WorkingEntity> workingEntitySet = workingRepository.findAllByEmployeeNo(id);
        Iterable<AdvanceEntity> advanceEntitySet = advanceRepository.findAllByEmployeeNo(id);
        if (workingEntitySet != null && advanceEntitySet != null) {
            workingEntitySet.forEach(workingEntity -> {

                workingRepository.delete(workingEntity);
            });
            advanceEntitySet.forEach(advanceEntity -> {

                advanceRepository.delete(advanceEntity);
            });

            employeeRep.delete(entity);

        } else {
            throw new IllegalArgumentException("Delete is not complete!");
        }

    }

    @Override
    public String deleteAll(List<Integer> ids) {
        StringBuilder result = new StringBuilder();
        for (Integer id : ids) {
            try {
                deleteEmployee(id);

            } catch (Exception e) {
                result.append(e.getMessage()).append("-");
            }
        }
        return result.toString().toString();
    }


    @Override
    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        EmployeeEntity entity = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee_id: " + id + " is not found!"));


        entity.setFullName(employeeDTO.getFullName());
        entity.setAge(employeeDTO.getAge());
        entity.setStartDay(employeeDTO.getStartDay());
        TeamEntity teamEntity = teamRep.findById(employeeDTO.getTeamID()).orElseThrow(() -> new IllegalArgumentException("Team is not found!"));
        entity.setTeam(teamEntity);
        entity.setAddress(employeeDTO.getAddress());
        entity.setMoneyPerHour(employeeDTO.getMoneyPerHour());




        return mapper.toDTO(employeeRep.save(entity));

    }




}
