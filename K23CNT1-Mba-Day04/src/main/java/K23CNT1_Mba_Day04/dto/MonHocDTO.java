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
public class MonHocDTO {
    // Mã môn học: chỉ gồm 2 ký tự
    @Size(min = 2, max = 2, message = "Mã môn học phải gồm 2 ký tự")
    private String mamh;

    // Tên môn học: độ dài 5-50 ký tự
    @Size(min = 5, max = 50, message = "Tên môn học phải từ 5 đến 50 ký tự")
    private String tenmh;

    // Số tiết: trong khoảng 45-75
    @Min(value = 45, message = "Số tiết tối thiểu là 45")
    @Max(value = 75, message = "Số tiết tối đa là 75")
    private int sotiet;
}