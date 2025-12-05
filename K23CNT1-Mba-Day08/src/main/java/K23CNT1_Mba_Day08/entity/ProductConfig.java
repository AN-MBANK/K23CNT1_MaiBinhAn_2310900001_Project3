package K23CNT1_Mba_Day08.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_config")
public class ProductConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính [cite: 904, 905]

    // Khóa ngoại trỏ đến Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    // Khóa ngoại trỏ đến Configuration
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configId")
    private Configuration configuration;

    // Thuộc tính bổ sung: Lưu giá trị cấu hình (ví dụ: "8GB", "Core i7") [cite: 904, 911]
    private String value;
}