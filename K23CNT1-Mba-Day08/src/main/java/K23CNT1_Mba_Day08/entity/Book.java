package K23CNT1_Mba_Day08.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book") // Ánh xạ tới bảng book trong DB [cite: 9]
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // id INT(11) [cite: 11]

    private String code; // code VARCHAR(255) [cite: 12]
    private String name; // name VARCHAR(255) [cite: 13]
    private String description; // description TEXT [cite: 14]
    private String imgUrl; // imgUrl VARCHAR(255) [cite: 15]
    private Integer quantity; // quantity INT(11) [cite: 16]
    private Double price; // price DOUBLE [cite: 17]
    private Boolean isActive; // isActive TINYINT(1) [cite: 18]

    // Tạo mối quan hệ Many-to-Many với bảng author [cite: 192, 193]
    @ManyToMany
    @JoinTable(
            name = "book_author", // Tên bảng trung gian [cite: 196]
            joinColumns = @JoinColumn(name = "bookId"), // Khóa ngoại tới Book [cite: 197]
            inverseJoinColumns = @JoinColumn(name = "authorId") // Khóa ngoại tới Author [cite: 195]
    )
    private List<Author> authors = new ArrayList<>(); // [cite: 198]
}