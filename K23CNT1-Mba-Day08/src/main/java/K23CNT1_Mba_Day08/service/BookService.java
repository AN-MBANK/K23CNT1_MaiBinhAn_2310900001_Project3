package K23CNT1_Mba_Day08.service;

import K23CNT1_Mba_Day08.entity.Book;
import K23CNT1_Mba_Day08.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // SỬA: Sử dụng phương thức Fetch Join để tránh lỗi Lazy Loading (500)
    public List<Book> getAllBooks() {
        // Gọi phương thức được định nghĩa trong BookRepository để tải Author/BookAuthor Eagerly
        return bookRepository.findAllWithAuthors();
    }

    public Book saveBook (Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook (Long id) {
        bookRepository.deleteById(id);
    }
}