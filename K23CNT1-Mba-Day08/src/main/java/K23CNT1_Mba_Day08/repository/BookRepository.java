package K23CNT1_Mba_Day08.repository;
// Trong BookRepository.java

import K23CNT1_Mba_Day08.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Sử dụng FETCH JOIN để tải các Entity liên kết ngay lập tức (EAGERLY)
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookAuthors ba LEFT JOIN FETCH ba.author")
    List<Book> findAllWithAuthors();
}