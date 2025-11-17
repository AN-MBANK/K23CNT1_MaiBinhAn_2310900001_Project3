package K23CNT1_Mba_Day04.controller;

import K23CNT1_Mba_Day04.dto.EmployeeDTO;
import K23CNT1_Mba_Day04.entity.Employee;
import K23CNT1_Mba_Day04.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeDTO dto) {
        employeeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully!");
    }
    // Thêm các API PUT và DELETE
}