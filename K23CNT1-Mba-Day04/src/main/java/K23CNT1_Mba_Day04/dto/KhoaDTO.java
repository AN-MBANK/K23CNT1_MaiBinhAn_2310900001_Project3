package K23CNT1_Mba_Day04.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KhoaDTO {

    @NotBlank(message = "Mã khoa không được để trống")
    @Size(min = 2, max = 10, message = "Mã khoa phải từ 2 đến 10 ký tự")
    private String makh;

    @Size(min = 5, max = 25, message = "Tên khoa phải từ 5 đến 25 ký tự")
    private String tenkh;
}