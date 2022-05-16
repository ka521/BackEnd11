package doanthuctap.controller;

import doanthuctap.entity.Advances;
import doanthuctap.exception.ResourceNotFoundException;
import doanthuctap.repository.AdvancesRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import doanthuctap.common.MessageResponse;
import doanthuctap.dto.AdvanceDTO;
import doanthuctap.service.AdvanceService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v4")
@CrossOrigin(origins = "http://localhost:3000")
public class AdvanceController {

    @Autowired
    private AdvancesRepository1 advancesRepository;

    // get all employees


    @GetMapping("/advances/{employeeId}" )
    public List<Advances> getAllWorkingById(@PathVariable int employeeId){
        return advancesRepository.findAllByEmployeeId(employeeId);
    }

    // create employee rest api
    @PostMapping("/advances")
    public Advances createAdvances(@RequestBody Advances advances) {

        return advancesRepository.save(advances);
    }

    @DeleteMapping("/advances/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAdvances(@PathVariable int id){
        Advances advances = advancesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("advances not exist with id :" + id));

        advancesRepository.delete(advances);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
