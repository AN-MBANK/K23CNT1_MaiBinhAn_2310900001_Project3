package K23CNT1_Mba_Day04.service;

import K23CNT1_Mba_Day04.dto.EmployeeDTO;
import K23CNT1_Mba_Day04.entity.Employee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private static Long counter = 0L;

    public EmployeeService() {
        // Khởi tạo dữ liệu mẫu
        employeeList.add(new Employee(++counter, "Alice Johnson", "Female", 35, 50000.0));
        employeeList.add(new Employee(++counter, "Bob Caden", "Male", 28, 45000.0));
    }

    public List<Employee> findAll() {
        return employeeList;
    }

    public boolean create(EmployeeDTO dto) {
        Employee newEmployee = Employee.builder()
                .id(++counter)
                .fullName(dto.getFullName())
                .gender(dto.getGender())
                .age(dto.getAge())
                .salary(dto.getSalary())
                .build();
        employeeList.add(newEmployee);
        return true;
    }
    // Thêm các hàm findById, update, delete tương tự MonHocService
}