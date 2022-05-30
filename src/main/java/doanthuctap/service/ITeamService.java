package doanthuctap.service;


import doanthuctap.dto.TeamDTO;

public interface ITeamService {
    TeamDTO getTeamById(int id);

    TeamDTO addTeam(TeamDTO teamDTO);
}
