package doanthuctap.service;


import doanthuctap.dto.TeamDTO;

public interface TeamService {
    TeamDTO getTeamById(int id);

    TeamDTO addTeam(TeamDTO teamDTO);
}
