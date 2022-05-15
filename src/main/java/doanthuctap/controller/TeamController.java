package doanthuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import doanthuctap.common.MessageResponse;
import doanthuctap.dto.TeamDTO;
import doanthuctap.service.TeamService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/team")
@CrossOrigin(origins = "http://localhost:3000")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/get-all")
    public ResponseEntity<?> listTeam() {
        try {
            return ResponseEntity.ok(teamService.listTeam());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }

    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findTeamByName(@RequestParam String name) {

        try {
            return ResponseEntity.ok(teamService.findTeamByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }


    }

    @PostMapping("/create")
    public ResponseEntity<?> addTeam(@RequestBody @Valid TeamDTO teamDTO) {
        try {
            return ResponseEntity.ok(teamService.addTeam(teamDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }

    }

    @PostMapping("/create-all")
    public ResponseEntity<?> createAll(@RequestBody @Valid List<TeamDTO> list) {
        try {
            list.forEach(teamDTO -> teamService.addTeam(teamDTO));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

    @PutMapping("/update-team/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Integer id, @RequestBody @Valid TeamDTO teamDTO) {
        try {
            return ResponseEntity.ok(teamService.updateTeam(id, teamDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Integer id) {
        try {
            teamService.delete(id);
            return ResponseEntity.ok().body("Delete Team is  successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

}
