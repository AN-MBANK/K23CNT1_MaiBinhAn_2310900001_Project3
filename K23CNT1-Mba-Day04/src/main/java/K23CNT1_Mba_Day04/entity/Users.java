package K23CNT1_Mba_Day04.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // Hoặc chỉ cần dùng @Data nếu chưa kết nối DB
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private LocalDate birthDay;
    private String email;
    private String phone;
    private int age;
    private Boolean status;
}