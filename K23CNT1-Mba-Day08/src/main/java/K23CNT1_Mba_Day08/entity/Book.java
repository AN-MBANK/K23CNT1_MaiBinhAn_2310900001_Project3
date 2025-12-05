package K23CNT1_Mba_Day08.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;
    private String imgUrl;
    private Integer quantity;
    private Double price;
    private Boolean isActive;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthor> bookAuthors = new ArrayList<>();

    // PHƯƠNG THỨC TIỆN ÍCH CHO THYMELEAF

    // 1. Kiểm tra Author đã được chọn (cho Checkbox)
    public boolean hasAuthor(Author author) {
        if (this.bookAuthors == null) {
            return false;
        }
        for (BookAuthor ba : this.bookAuthors) {
            // So sánh ID của Author
            if (ba.getAuthor() != null && ba.getAuthor().getId().equals(author.getId())) {
                return true;
            }
        }
        return false;
    }

    // 2. Kiểm tra Author có phải là Chủ biên không (cho Radio Button)
    public boolean isEditor(Author author) {
        if (this.bookAuthors == null) {
            return false;
        }
        for (BookAuthor ba : this.bookAuthors) {
            // Kiểm tra ID khớp và vai trò là Chủ biên
            if (ba.getAuthor() != null && ba.getAuthor().getId().equals(author.getId()) && ba.getIsEditor() != null && ba.getIsEditor()) {
                return true;
            }
        }
        return false;
    }
}