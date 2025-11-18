package K23CNT1_Mba_Day08.controller;

import K23CNT1_Mba_Day08.entity.Author;
import K23CNT1_Mba_Day08.entity.Book;
import K23CNT1_Mba_Day08.service.AuthorService;
import K23CNT1_Mba_Day08.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    // Đường dẫn tĩnh để lưu ảnh
    private static final String UPLOAD_DIR = "src/main/resources/static/";
    private static final String UPLOAD_PathFile = "images/products/";

    // Hiển thị toàn bộ sách (Read - List)
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/book-list";
    }

    // Form thêm mới sách (Create - Show Form)
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/book-form";
    }

    // Xử lý thêm mới/sửa sách (Create/Update - Process Form)
    @PostMapping({"/new", "/edit"})
    public String saveBook(
            @ModelAttribute Book book,
            // Đặt required = false để cho phép form submit khi không chọn tác giả
            @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
            @RequestParam("imageBook") MultipartFile imageFile) {

        // 1. Kiểm tra và xử lý List<Long> authorIds nếu nó là null
        if (authorIds == null) {
            authorIds = new ArrayList<>();
        }

        // 2. Xử lý upload ảnh
        if (!imageFile.isEmpty()) {
            try {
                // Tạo thư mục nếu chưa tồn tại
                Path uploadPath = Paths.get(UPLOAD_DIR + UPLOAD_PathFile);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Xử lý tên file
                String originalFilename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName = book.getCode() + fileExtension;

                // Lưu file lên server
                Path filePath = uploadPath.resolve(newFileName);
                Files.copy(imageFile.getInputStream(), filePath);

                // Lưu đường dẫn ảnh
                book.setImgUrl("/" + UPLOAD_PathFile + newFileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (book.getId() != null && (book.getImgUrl() == null || book.getImgUrl().isEmpty())) {
            // Giữ nguyên imgUrl cũ nếu là update và không upload file mới
            Book existingBook = bookService.getBookById(book.getId());
            if (existingBook != null) {
                book.setImgUrl(existingBook.getImgUrl());
            }
        }

        // 3. Liên kết Authors với Book và lưu
        List<Author> authors = new ArrayList<>(authorService.findAllById(authorIds));
        book.setAuthors(authors);
        bookService.saveBook(book);

        return "redirect:/books";
    }

    // Form sửa thông tin sách (Update - Show Form)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/book-form";
    }

    // Xóa sách (Delete)
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}