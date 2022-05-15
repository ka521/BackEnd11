package doanthuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import doanthuctap.common.MessageResponse;
import doanthuctap.dto.AdvanceDTO;
import doanthuctap.service.AdvanceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/advance")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvanceController {
    @Autowired
    private AdvanceService advanceService;

    @GetMapping("/get-all/{employee_id}")
    public ResponseEntity<?> listAdvance(@PathVariable Integer employee_id) {
        try {
            return ResponseEntity.ok(advanceService.listAdvance(employee_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> addAdvance(@RequestBody @Valid AdvanceDTO advanceDTO) {
        try {

            return ResponseEntity.ok(advanceService.addAdvance(advanceDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }

    }

    @DeleteMapping("/delete/{advance_id}")
    public ResponseEntity<?> deleteAdvance(@PathVariable Integer advance_id) {
        try {
            advanceService.deleteAdvance(advance_id);
            return ResponseEntity.ok().body("Delete Advance is successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }


    }

}
