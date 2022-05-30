
package doanthuctap.service.ServiceImpl;


import doanthuctap.dto.TeamDTO;
import doanthuctap.entity.Team;
import doanthuctap.mapper.Mapper;
import doanthuctap.repository.TeamRepository;
import doanthuctap.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeamService implements ITeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private Mapper mapper;


    @Override
    public TeamDTO addTeam(TeamDTO teamDTO) {
        Team teamEntity = teamRepository.save(mapper.toTeamEntity(teamDTO));
        return mapper.toTeamDTO(teamEntity);
    }

    @Override
    public TeamDTO getTeamById(int id)       {
        Team team=teamRepository.getById(id);
        TeamDTO teamDTO=mapper.toTeamDTO(team);
        return  teamDTO;
    }
}


