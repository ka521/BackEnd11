package doanthuctap.service;

import doanthuctap.dto.TeamDTO;

import java.util.Set;

public interface TeamService {
    Set<TeamDTO> listTeam();



    TeamDTO addTeam(TeamDTO teamDTO);



    void delete(Integer id);
}
