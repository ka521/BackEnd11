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
//@GetMapping("/query/{fruitname}")
//public ResponseEntity<Fruit> queryFruit(@PathVariable String fruitname){
//        if (fruitname.equalsIgnoreCase("banana"))
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity(new Fruit(fruitname, "Orange"), HttpStatus.OK);
//        }

// CommandLineRunner run() {
//         return args -> {
//         // Lấy ra 5 user đầu tiên
//         // PageRequest.of(0,5) tương đương với lấy ra page đầu tiên, và mỗi page sẽ có 5 phần tử
//         Page<User> page = userRepository.findAll(PageRequest.of(0, 5));
//        // In ra 5 user đầu tiên
//        System.out.println("In ra 5 user đầu tiên: ");
//        page.forEach(System.out::println);
//        // Lấy ra 5 user tiếp theo
//        page = userRepository.findAll(page.nextPageable());
//
//        System.out.println("In ra 5 user tiếp theo: ");
//        page.forEach(System.out::println);
//
//        System.out.println("In ra số lượng user ở page hiện tại: " + page.getSize());
//        System.out.println("In ra tổng số lượng user: " + page.getTotalElements());
//        System.out.println("In ra tổng số page: " + page.getTotalPages());
//
//        // Lấy ra 5 user ở page 1, sort theo tên
//        page = userRepository.findAll(PageRequest.of(1, 5, Sort.by("name").descending()));
//        System.out.println("In ra 5 user page 1, sắp xếp theo name descending:");
//        page.forEach(System.out::println);
//
//        // Custom method
//        List<User> list = userRepository.findAllByNameLike("name-%", PageRequest.of(0, 5));
//        System.out.println(list);
//        };
//        }
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
//    public static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

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
//        Optional<EmployeeModel> foundEmployee = employeeRepository.findById(employee.getEmployeeId());
//        if (foundEmployee.equals(true)) {
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new ResponseObject("fail", "Employee already taken", "")
//            );
//        }
//        Integer team=Integer.parseInt(teamId);
//        System.out.println(teamId);
        try {
            //save files to a folder => use a service
            String imageUrl = "https://i.stack.imgur.com/l60Hf.png";

            if (!file.isEmpty()) {
                String generateFileName = storageService.storeFile(file);
                imageUrl = "http://localhost:8080/api/v1/FileUpload/files/" + generateFileName;
            }
//            EmployeeModel employee = new EmployeeModel();
//            System.out.println(employeeDto);
            System.out.println(employee);
            employee.setImageURL(imageUrl);
//            employee.getEmployeeTeam().setTeamId(team);
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

//                    employee.setImageURL(newEmployee.getImageURL());
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
//        if(listContact.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        return employeeRepository.findAll();
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
//        return employeeRepository.findAll();
    }

//
//    @PutMapping("{id}")
//    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable("id") int id
//            , @RequestBody EmployeeModel employee) {
//        return new ResponseEntity<EmployeeModel>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
//
//        // delete employee from DB
//        employeeService.deleteEmployee(id);
//        System.out.println(id);
//
//        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
//    }
//    @GetMapping(value = "/list/{name}")
//    public List<EmployeeModel> findEmployee(@PathVariable("name") String name){
//        return employeeService.findEmployeeByName();
//    }

//    @GetMapping(value = "/name/{name}")
//    public List<EmployeeModel> findEmployeeById(@PathVariable("name") String employeeName) {
////        return employeeService.getEmployeeById(employeeId);
//        System.out.println(employeeName);
//        return employeeService.findByName(employeeName);
//    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable("id") int employeeId) {
//        return employeeService.getEmployeeById(employeeId);
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

    //Chuẩn response
    @GetMapping(value = "/working/{id}")
    public ResponseEntity<ResponseObject> findMyAnEmployee(@PathVariable int id) {
        Optional<EmployeeModel> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query successfully", foundEmployee)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cannot find employee with id = " + id, "")
            );
        }
    }
}
