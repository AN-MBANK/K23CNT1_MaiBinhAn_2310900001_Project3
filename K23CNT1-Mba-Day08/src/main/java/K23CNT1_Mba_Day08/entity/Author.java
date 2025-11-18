package K23CNT1_Mba_Day08.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author") // Ánh xạ tới bảng author trong DB [cite: 20]
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // id INT(11) [cite: 22]

    // Thêm các thuộc tính còn thiếu từ mô hình DB [cite: 22, 23, 24, 25, 26, 30, 32, 34, 36]
    private String code;
    private String name;
    private String description;
    private String imgUrl;
    private String email;
    private String phone;
    private String address;
    private Boolean isActive;

    // Tạo mối quan hệ Many-to-Many với bảng book [cite: 238]
    @ManyToMany(mappedBy = "authors") // Chỉ định rằng mối quan hệ đã được ánh xạ bởi thuộc tính "authors" trong Book [cite: 239, 242]
    private List<Book> books = new ArrayList<>(); // [cite: 240, 243]
}