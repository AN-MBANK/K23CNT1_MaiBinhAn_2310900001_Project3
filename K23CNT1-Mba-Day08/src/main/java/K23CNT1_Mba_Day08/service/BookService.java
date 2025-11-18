package K23CNT1_Mba_Day08.service;

import K23CNT1_Mba_Day08.entity.Book;
import K23CNT1_Mba_Day08.repository.BookRepository; // [cite: 287]
import org.springframework.beans.factory.annotation.Autowired; // [cite: 288]
import org.springframework.stereotype.Service; // [cite: 288]
import java.util.List; // [cite: 289]

@Service // [cite: 290]
public class BookService {

    @Autowired // [cite: 292]
    private BookRepository bookRepository; // [cite: 293]

    public List<Book> getAllBooks() { // [cite: 304]
        return bookRepository.findAll(); // [cite: 305]
    }

    public Book saveBook(Book book) { // [cite: 307]
        return bookRepository.save(book); // [cite: 308]
    }

    public Book getBookById(Long id) { // [cite: 310]
        return bookRepository.findById(id).orElse(null); // [cite: 311]
    }

    public void deleteBook(Long id) { // [cite: 313]
        bookRepository.deleteById(id); // [cite: 314]
    }
}