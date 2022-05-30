package doanthuctap.controller;

import doanthuctap.common.MessageResponse;
import doanthuctap.dto.EmployeeDTO;
import doanthuctap.entity.EmployeeModel;
import doanthuctap.repository.EmployeeRepository;
import doanthuctap.response.ResponseObject;
import doanthuctap.service.IstorageService;
import doanthuctap.service.ServiceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    private IstorageService storageService;

    //CRUD api
    @PostMapping()
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody EmployeeModel employee) {
        return new ResponseEntity<EmployeeModel>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    ResponseEntity<ResponseObject> insertEmployee(@RequestParam("file") MultipartFile file, @ModelAttribute EmployeeModel employee) {

        try {

            String imageUrl = "https://i.stack.imgur.com/l60Hf.png";

            if (!file.isEmpty()) {
                String generateFileName = storageService.storeFile(file);
                imageUrl = "http://localhost:8080/api/v1/FileUpload/files/" + generateFileName;
            }

            System.out.println(employee);
            employee.setImageURL(imageUrl);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Insert Employee sucessfully", employeeRepository.save(employee))
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("OK", exception.getMessage(), "")
            );
        }
    }




    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateEmployee(@RequestParam(value = "file", required = false) MultipartFile file, @ModelAttribute EmployeeModel newEmployee, @PathVariable int id) {
        try {

            EmployeeModel updateEmployee = employeeRepository.getById(id);
            String imageUrl = updateEmployee.getImageURL();
            if (!file.isEmpty()) {
                String generateFileName = storageService.storeFile(file);
                imageUrl = "http://localhost:8080/api/v1/FileUpload/files/" + generateFileName;
            }
            String finalImageUrl = imageUrl;


            updateEmployee.setFullName(newEmployee.getFullName());
            updateEmployee.setAge(newEmployee.getAge());
            updateEmployee.setEmployeeTeam(newEmployee.getEmployeeTeam());
            updateEmployee.setGender(newEmployee.getGender());
            updateEmployee.setAddress(newEmployee.getAddress());
            updateEmployee.setPhoneNumber(newEmployee.getPhoneNumber());
            updateEmployee.setStartDay(newEmployee.getStartDay());
            updateEmployee.setMoneyPerHour(newEmployee.getMoneyPerHour());
            updateEmployee.setTotalHours(newEmployee.getTotalHours());
            updateEmployee.setImageURL(newEmployee.getImageURL());
            updateEmployee.setImageURL(finalImageUrl);


            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update sucessfully", employeeRepository.save(updateEmployee))
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("OK", exception.getMessage(), "")
            );
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

    @DeleteMapping("/delete")
    ResponseEntity<ResponseObject> deleteManyEmployeeById(@RequestBody List<Integer> ids) {

        employeeRepository.deleteAllById(ids);
//        employeeRepository.deleteAllByIdInBatch(ids);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete employee Successfully", ""));
    }

    @GetMapping(value = "/list")
    public List<EmployeeDTO> getAllEmployee() {
        System.out.println("hello");
        return employeeService.getAllEmployees();

    }

    @GetMapping(value = "/listEmployee")
    public ResponseEntity<ResponseObject> getAllEmployeeNoPagination() {
        System.out.println("hello");
        List<EmployeeDTO> ListEmployee = employeeService.getAllEmployees();
        if (ListEmployee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("ok", "No Employee In List", ""));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Cannot find employee with id = ", ListEmployee)
            );
        }

    }


    @GetMapping(value = "{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable("id") int employeeId) {

        try {
            System.out.println(employeeId);
            return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
        } catch (Exception exception) {
            return null;
        }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(employeeService.findEmployee(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }

    }



}
