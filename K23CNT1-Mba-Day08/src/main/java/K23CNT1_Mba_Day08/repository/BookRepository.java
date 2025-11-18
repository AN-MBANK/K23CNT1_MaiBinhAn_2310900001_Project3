package K23CNT1_Mba_Day08.repository;

import K23CNT1_Mba_Day08.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository; // [cite: 258]
import org.springframework.stereotype.Repository; // [cite: 268]

@Repository
public interface BookRepository extends JpaRepository<Book, Long> { // [cite: 269, 270]
}