package doanthuctap.controller;



import doanthuctap.common.MessageResponse;
import doanthuctap.dto.TeamDTO;
import doanthuctap.entity.EmployeeModel;
import doanthuctap.entity.Team;
import doanthuctap.repository.EmployeeRepository;
import doanthuctap.repository.TeamRepository;
import doanthuctap.response.ResponseObject;
import doanthuctap.service.ServiceImpl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/team")
public class TeamController {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamService teamService;
    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping(value = "/listTeam")
    public List<Team> getAllTeam() {
        return teamRepository.findAll();

    }

    @GetMapping(value = "/{id}")
    public TeamDTO getTeam(@PathVariable("id") int id) {
        return teamService.getTeamById(id);

    }

@PostMapping("/create")
public ResponseEntity<?> addTeam(@RequestBody @Valid TeamDTO teamDTO) {
    try {
        return ResponseEntity.ok(teamService.addTeam(teamDTO));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

    }

}

}
