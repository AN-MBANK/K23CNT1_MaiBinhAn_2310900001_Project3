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
public class Khoa {
    @Id
    private String makh; // Mã khoa (Primary Key)
    private String tenkh; // Tên khoa
}