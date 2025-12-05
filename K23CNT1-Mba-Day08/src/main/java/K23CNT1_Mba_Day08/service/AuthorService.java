package K23CNT1_Mba_Day08.service;

import K23CNT1_Mba_Day08.entity.Author;
import K23CNT1_Mba_Day08.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author saveAuthor (Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById (Long id) {
        return authorRepository.findById(id).orElse (null);
    }

    public void deleteAuthor (Long id) {
        authorRepository.deleteById(id);
    }

    // Phương thức cần thiết để lấy danh sách tác giả theo IDs khi lưu Book
    public List<Author> findAllById (List<Long> ids) {
        return authorRepository.findAllById(ids);
    }
}