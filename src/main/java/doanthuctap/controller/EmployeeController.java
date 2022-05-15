package doanthuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import doanthuctap.common.MessageResponse;
import doanthuctap.dto.EmployeeDTO;
import doanthuctap.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(employeeService.listEmployee());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping("/get-page/{index}")
    public ResponseEntity<?> getPage(@PathVariable Integer index) {
        try {
            return ResponseEntity.ok(employeeService.getPage(index - 1));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }

    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(employeeService.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }

    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(employeeService.findEmployee(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }

    }

    @GetMapping("/find-by-team/{teamID}")
    public ResponseEntity<?> listEmployeeByTeam(@PathVariable Integer teamID) {
        try {
            return ResponseEntity.ok(employeeService.listEmployeeByTeam(teamID));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> newEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        try {
            return ResponseEntity.ok(employeeService.newEmployee(employeeDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/create-all")
    public ResponseEntity<?> createAll(@RequestBody @Valid List<EmployeeDTO> list) {
        try {
            list.forEach(employeeDTO -> employeeService.newEmployee(employeeDTO));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @PutMapping("/update/{employee_id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer employee_id, @RequestBody @Valid EmployeeDTO employeeDTO) {

        try {
            return ResponseEntity.ok(employeeService.updateEmployee(employee_id, employeeDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().body("Delete Employee is successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }

    }

    @DeleteMapping("/delete-multi")
    public ResponseEntity<?> deleteAll(@RequestParam(value = "ids") List<Integer> ids) {
        String s = employeeService.deleteAll(ids);
        return ResponseEntity.ok().body(s.isEmpty() ? "Delete Employees is successful!" : s);

    }


}
