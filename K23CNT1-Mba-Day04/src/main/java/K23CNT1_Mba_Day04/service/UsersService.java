package K23CNT1_Mba_Day04.service;

import K23CNT1_Mba_Day04.dto.UsersDTO;
import K23CNT1_Mba_Day04.entity.Users;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    private final List<Users> userList = new ArrayList<>();
    private static Long counter = 0L;

    public UsersService() {
        // Khởi tạo dữ liệu mẫu
        userList.add(new Users(++counter, "user1", "pass1A", "John Doe", LocalDate.parse("1991-01-01"), "john@example.com", "0987654321", 34, true));
        userList.add(new Users(++counter, "user2", "pass2B", "Jane Smith", LocalDate.parse("1992-05-15"), "jane@example.com", "0987654322", 33, false));
    }

    public List<Users> findAll() {
        return userList;
    }

    public boolean create(UsersDTO usersDTO) {
        // Chuyển UsersDTO thành Users Entity
        try {
            Users user = Users.builder()
                    .id(++counter)
                    .username(usersDTO.getUsername())
                    .password(usersDTO.getPassword())
                    .email(usersDTO.getEmail())
                    .fullName(usersDTO.getFullName())
                    .phone(usersDTO.getPhone())
                    .age(usersDTO.getAge())
                    .birthDay(usersDTO.getBirthDay())
                    .status(usersDTO.getStatus())
                    .build();

            // Logic kiểm tra trùng lặp (ví dụ: username)
            if (userList.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
                return false; // Đã tồn tại
            }

            userList.add(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // ... Thêm các phương thức update, delete khác nếu cần
}