package doanthuctap.controller;

import doanthuctap.entity.Working;
import doanthuctap.entity.WorkingEntity;
import doanthuctap.repository.WorkingRepository;
import doanthuctap.repository.WorkingRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import doanthuctap.common.MessageResponse;
import doanthuctap.dto.WorkingDTO;
import doanthuctap.service.WorkingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/working")
@CrossOrigin(origins = "http://localhost:3000")
public class WorkingController {
    @Autowired
    private WorkingService workingService;



    @Autowired
    private WorkingRepository workingRepository;

    @Autowired
    private WorkingRepository1 workingRepository1;


    //get all working theo id employee
    @GetMapping("/works/{employeeId}" )
    public List<Working> getAllWorkingById(@PathVariable int employeeId){
        return workingRepository1.findAllByEmployeeId(employeeId);
    }

    @PostMapping("/works")
    public Working createWork(@RequestBody Working working) {

        return workingRepository1.save(working);
    }



    @DeleteMapping("/delete/{working_id}")
    public ResponseEntity<?> deleteWorking(@PathVariable Integer working_id) {
        try {
            workingService.deleteWorking(working_id);
            return ResponseEntity.ok().body("Delete Working is successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }


    }



}
