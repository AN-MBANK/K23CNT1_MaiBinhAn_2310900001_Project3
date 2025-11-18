package K23CNT1_Mba_Day08.service;

import K23CNT1_Mba_Day08.entity.Author;
import K23CNT1_Mba_Day08.repository.AuthorRepository; // [cite: 321]
import org.springframework.beans.factory.annotation.Autowired; // [cite: 323]
import org.springframework.stereotype.Service; // [cite: 323]
import java.util.List; // [cite: 324]

@Service // [cite: 325]
public class AuthorService {

    @Autowired // [cite: 327]
    private AuthorRepository authorRepository; // [cite: 328]

    public List<Author> getAllAuthors() { // [cite: 329]
        return authorRepository.findAll(); // [cite: 330]
    }

    public Author saveAuthor(Author author) { // [cite: 332]
        return authorRepository.save(author); // [cite: 333]
    }

    public Author getAuthorById(Long id) { // [cite: 335]
        return authorRepository.findById(id).orElse(null); // [cite: 336]
    }

    public void deleteAuthor(Long id) { // [cite: 348]
        authorRepository.deleteById(id); // [cite: 350]
    }

    public List<Author> findAllById(List<Long> ids) { // [cite: 352]
        return authorRepository.findAllById(ids); // [cite: 353]
    }
}