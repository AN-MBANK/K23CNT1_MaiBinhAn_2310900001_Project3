package K23cnt1.Day03.controlller;

import K23cnt1.Day03.entity.MbaStudent;
import K23cnt1.Day03.service.MbaStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mba-students")
public class MbaStudentController {

    @Autowired
    private MbaStudentService MbastudentService;

    // Lấy danh sách tất cả sinh viên
    @GetMapping
    public List<MbaStudent> getAllStudents() {
        return MbastudentService.getStudents();
    }

    // Lấy thông tin sinh viên theo ID
    @GetMapping("/{id}")
    public MbaStudent getStudentById(@PathVariable Long id) {
        return MbastudentService.getStudentById(id);

    }

    // Thêm sinh viên mới
    @PostMapping
    public MbaStudent addStudent(@RequestBody MbaStudent student) {
        return MbastudentService.addStudent(student);
    }

    // Cập nhật thông tin sinh viên theo ID
    @PutMapping("/{id}")
    public MbaStudent updateStudent(@PathVariable Long id, @RequestBody MbaStudent student) {
        return MbastudentService.updateStudent(id, student);
    }

    // Xóa sinh viên theo ID
    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable Long id) {
        return MbastudentService.deleteStudent(id);
    }
}
