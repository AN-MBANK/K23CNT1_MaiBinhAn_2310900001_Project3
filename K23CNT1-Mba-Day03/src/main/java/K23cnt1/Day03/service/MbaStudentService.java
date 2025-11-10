package K23cnt1.Day03.service;

import K23cnt1.Day03.entity.MbaStudent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
        * Service class: StudentService
 * <p>Lớp dịch vụ thực hiện các chức năng thao tác với List
Object Student</p>
        *
        * @author MaiBinhAn
 * @version 1.0
        */
@Service
public class MbaStudentService {

    private static final List<MbaStudent> students = new ArrayList<>();

    public MbaStudentService() {
        students.addAll(Arrays.asList(
                new MbaStudent(1L, "Nguyễn Văn A", 22, "Nam", "Hà Nội", "0909123456", "vana@gmail.com"),
                new MbaStudent(2L, "Trần Thị B", 21, "Nữ", "Hải Phòng", "0912333444", "thib@gmail.com"),
                new MbaStudent(3L, "Lê Văn C", 23, "Nam", "Đà Nẵng", "0988777666", "vanc@gmail.com"),
                new MbaStudent(4L, "Phạm Thị D", 20, "Nữ", "TP. Hồ Chí Minh", "0909333555", "thid@gmail.com"),
                new MbaStudent(5L, "Hoàng Văn E", 24, "Nam", "Huế", "0912555888", "vane@gmail.com"),
                new MbaStudent(6L, "Đặng Thị F", 22, "Nữ", "Nam Định", "0909666777", "thif@gmail.com"),
                new MbaStudent(7L, "Phan Văn G", 23, "Nam", "Bắc Ninh", "0913444555", "vang@gmail.com"),
                new MbaStudent(8L, "Ngô Thị H", 21, "Nữ", "Thái Bình", "0988111222", "thih@gmail.com"),
                new MbaStudent(9L, "Bùi Văn I", 25, "Nam", "Nghệ An", "0909777888", "vani@gmail.com"),
                new MbaStudent(10L, "Vũ Thị K", 20, "Nữ", "Thanh Hóa", "0912999000", "thik@gmail.com")
        ));
    }

    // Trả về toàn bộ danh sách
    public List<MbaStudent> getStudents() {
        return students;
    }

    // Lấy sinh viên theo id
    public MbaStudent getStudentById(Long id) {
        return students.stream()
                .filter(s -> s.getMbaId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Thêm sinh viên
    public MbaStudent addStudent(MbaStudent student) {
        students.add(student);
        return student;
    }

    // Cập nhật sinh viên
    public MbaStudent updateStudent(Long id, MbaStudent newInfo) {
        for (MbaStudent s : students) {
            if (s.getMbaId().equals(id)) {
                s.setMbaName(newInfo.getMbaName());
                s.setMbaAge(newInfo.getMbaAge());
                s.setMbaGender(newInfo.getMbaGender());
                s.setMbaAddress(newInfo.getMbaAddress());
                s.setMbaPhone(newInfo.getMbaPhone());
                s.setMbaEmail(newInfo.getMbaEmail());
                return s;
            }
        }
        return null;
    }

    // Xóa sinh viên theo id
    public boolean deleteStudent(Long id) {
        return students.removeIf(s -> s.getMbaId().equals(id));
    }
}
