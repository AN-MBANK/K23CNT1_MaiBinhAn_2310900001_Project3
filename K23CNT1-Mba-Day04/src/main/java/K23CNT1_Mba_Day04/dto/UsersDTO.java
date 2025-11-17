package K23CNT1_Mba_Day04.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    // 1. Username
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    // 2. Password
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    // Regex: Phải chứa ít nhất 1 chữ cái và 1 số
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$",
            message = "Password must contain at least one letter and one number")
    private String password;

    // 3. FullName
    @NotBlank(message = "Full name cannot be blank")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters")
    private String fullName;

    // 4. Birthday
    @Past(message = "Birthday must be in the past")
    private LocalDate birthDay;

    // 5. Email
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    // 6. Phone
    // Regex cho số điện thoại Việt Nam (ví dụ)
    @Pattern(regexp = "(\\+84|0)\\d{9,10}", message = "Phone number is invalid")
    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    // 7. Age
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private int age;

    // 8. Status
    @NotNull(message = "Status cannot be null")
    private Boolean status;
}