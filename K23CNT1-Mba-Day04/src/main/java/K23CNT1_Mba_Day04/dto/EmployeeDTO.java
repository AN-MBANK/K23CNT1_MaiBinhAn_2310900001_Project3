package K23CNT1_Mba_Day04.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    // ID được sinh tự động trong Entity, không cần trong DTO

    // Họ và tên: 3-25 ký tự
    @Size(min = 3, max = 25, message = "Họ và tên phải từ 3 đến 25 ký tự")
    private String fullName;

    // Giới tính (không có ràng buộc cụ thể)
    private String gender;

    // Tuổi: 18-60
    @Min(value = 18, message = "Tuổi phải từ 18 trở lên")
    @Max(value = 60, message = "Tuổi tối đa là 60")
    private int age;

    // Lương: > 0
    @Min(value = 1, message = "Lương phải lớn hơn 0")
    private double salary;
}