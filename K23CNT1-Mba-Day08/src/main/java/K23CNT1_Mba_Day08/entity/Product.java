package K23CNT1_Mba_Day08.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự động tăng [cite: 899]
    private String code;
    private String name;
    private String imgUrl;
    private Integer quantity;
    private Double price;
    private Boolean isActive; // TINYINT(1) [cite: 898]

    // Quan hệ One-to-Many với bảng liên kết ProductConfig
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductConfig> productConfigs = new ArrayList<>();
}