package K23CNT1_Mba_Day08.repository;

import K23CNT1_Mba_Day08.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository; // [cite: 277]
import org.springframework.stereotype.Repository; // [cite: 278]

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> { // [cite: 279, 280]
}