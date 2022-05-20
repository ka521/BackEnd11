package doanthuctap.service.ServiceImpl;

import doanthuctap.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import doanthuctap.dto.TeamDTO;
import doanthuctap.entity.TeamEntity;
import doanthuctap.mapper.TeamMapper;
import doanthuctap.service.TeamService;

import java.util.HashSet;
import java.util.Set;

@Service

public class TeamImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMapper mapper;

    @Override
    public Set<TeamDTO> listTeam() {
        Set<TeamDTO> dtoSet = new HashSet<>();
        teamRepository.findAll().forEach(team -> {
            dtoSet.add(mapper.toDTO(team));
        });
        return dtoSet;
    }



    @Override
    public TeamDTO addTeam(TeamDTO teamDTO) {
        TeamEntity teamEntity = teamRepository.save(mapper.toEntity(teamDTO));
        return mapper.toDTO(teamEntity);
    }



    @Override
    public void delete(Integer id) {
        TeamEntity entity = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team is not found!"));
        teamRepository.delete(entity);
    }
}
