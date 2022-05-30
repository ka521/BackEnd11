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
//        return employeeRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public TeamDTO getTeam(@PathVariable("id") int id) {
        return teamService.getTeamById(id);
//        return employeeRepository.findAll();
    }

//    @PostMapping(value="/insert")
//    ResponseEntity<ResponseObject> addNewTeam(@ModelAttribute Team team) {
////        Optional<WorkingModel> foundWorking = workingRepository.findById(working.getWorkingId());
////        if (foundWorking.equals(true)) {
////            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
////                    new ResponseObject("fail", "Working already taken", "")
////            );
////        }
////        WorkingModel working = new WorkingModel();
////        System.out.println(req);
////        EmployeeModel employeeModel = employeeRepository.findById(working.getEmployeeModel().getEmployeeId()).get();
////        working.setEmployeeModel(employeeModel);
//        System.out.println(team);
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("OK", "Create New Team sucessfully", teamRepository.save(team))
//        );
//    }
@PostMapping("/create")
public ResponseEntity<?> addTeam(@RequestBody @Valid TeamDTO teamDTO) {
    try {
        return ResponseEntity.ok(teamService.addTeam(teamDTO));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

    }

}

}
