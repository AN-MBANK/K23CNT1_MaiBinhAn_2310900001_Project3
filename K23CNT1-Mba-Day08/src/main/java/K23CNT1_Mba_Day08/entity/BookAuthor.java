package K23CNT1_Mba_Day08.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_author")
public class BookAuthor {

    // Khóa chính tự tăng cho bảng trung gian
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quan hệ Many-to-One với Book
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    private Book book;

    // Quan hệ Many-to-One với Author
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorid")
    private Author author;

    // Thuộc tính mở rộng: 1=Chủ biên, 0=Đồng tác giả
    @Column(name = "is_editor")
    private Boolean isEditor;
}