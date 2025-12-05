package K23CNT1_Mba_Day08.entity;

import jakarta.persistence.*;
import lombok.*;
// SỬA LỖI: Import đúng
import java.util.List;

import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính [cite: 900, 901]
    private String name; // Ví dụ: "Ram", "CPU", "Card" [cite: 903]
    private Boolean isActive; // TINYINT(1) [cite: 900]

    // Quan hệ One-to-Many với bảng liên kết ProductConfig
    @OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductConfig> productConfigs = new ArrayList<>();
}