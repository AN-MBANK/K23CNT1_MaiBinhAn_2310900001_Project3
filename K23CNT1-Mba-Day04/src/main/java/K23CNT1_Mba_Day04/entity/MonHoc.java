package K23CNT1_Mba_Day04.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MonHoc {
    @Id
    private String mamh;    // Mã môn học
    private String tenmh;   // Tên môn học
    private int sotiet;     // Số tiết
}