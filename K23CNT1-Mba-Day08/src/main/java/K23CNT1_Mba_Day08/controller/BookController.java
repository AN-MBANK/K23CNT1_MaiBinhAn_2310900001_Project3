package K23CNT1_Mba_Day08.controller;

import K23CNT1_Mba_Day08.entity.Author;
import K23CNT1_Mba_Day08.entity.Book;
import K23CNT1_Mba_Day08.entity.BookAuthor;
import K23CNT1_Mba_Day08.repository.BookAuthorRepository;
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
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/";
    private static final String UPLOAD_PathFile = "images/products/";

    // --- CÁC PHƯƠNG THỨC GET (Giữ nguyên) ---

    @GetMapping
    public String listBooks (Model model) {
        // Sử dụng Fetch Join để tránh lỗi Lazy Loading
        model.addAttribute("books", bookService.getAllBooks());
        return "books/book-list";
    }

    @GetMapping("/new")
    public String showCreateForm (Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/book-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/book-form";
    }

    // --- PHƯƠNG THỨC SAVEBOOK ĐÃ THÊM TRY-CATCH ---
    @PostMapping("/new")
    public String saveBook(@ModelAttribute Book book,
                           @RequestParam(required = false) List<Long> authorIds,
                           @RequestParam(required = false) Long editorId,
                           @RequestParam("imageBook") MultipartFile imageFile,
                           Model model) { // THÊM MODEL VÀO ĐÂY

        try {
            // --- 1. Xử lý Upload ảnh (Fix File Lock) ---
            String newFileName = null;
            if(!imageFile.isEmpty()) {
                Path uploadPath = Paths.get(UPLOAD_DIR + UPLOAD_PathFile);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories (uploadPath);
                }
                String originalFilename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String fileExtension = originalFilename.substring (originalFilename.lastIndexOf("."));
                newFileName = book.getCode() + fileExtension;

                Path filePath = uploadPath.resolve(newFileName);

                // Sử dụng try-with-resources để đảm bảo InputStream được đóng
                try (var inputStream = imageFile.getInputStream()) {
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                book.setImgUrl("/" + UPLOAD_PathFile + newFileName);
            }

            // --- 2. Xử lý Quan hệ One-to-Many với BookAuthor ---

            // Xóa quan hệ cũ nếu là UPDATE
            if (book.getId() != null && book.getBookAuthors() != null) {
                // Lệnh này TẠO ra vấn đề: book.getBookAuthors().clear();
                // Nếu bạn không thể xóa hoàn toàn lệnh clear, bạn cần tải Book trước

                // Tải Entity đã tồn tại từ DB để quản lý BookAuthors (Nếu là UPDATE)
                Book existingBook = bookService.getBookById(book.getId());

                // Nếu có dữ liệu cũ, xóa nó ra khỏi bộ sưu tập được quản lý bởi Hibernate
                if (existingBook != null) {
                    existingBook.getBookAuthors().clear();
                }

                // Cần lưu Book (existingBook) để Hibernate xử lý xóa Orphan Removal
                bookService.saveBook(existingBook);
            }

            // Bỏ Dòng này: Book savedBook = bookService.saveBook (book); // LƯU SAI CHỖ

            // Tạo và Gán các đối tượng BookAuthor mới
            if (authorIds != null && !authorIds.isEmpty()) {
                List<Author> authors = authorService.findAllById(authorIds);
                List<BookAuthor> newBookAuthors = new ArrayList<>();

                for (Author author : authors) {
                    BookAuthor ba = new BookAuthor();
                    // Ba.setBook(savedBook); // CẦN GÁN BOOK BẰNG ĐỐI TƯỢNG BOOK ĐẦU VÀO
                    ba.setBook(book); // Gán Book (đối tượng @ModelAttribute)
                    ba.setAuthor(author);

                    // Logic gán vai trò isEditor
                    ba.setIsEditor(editorId != null && author.getId().equals(editorId));

                    newBookAuthors.add(ba);
                }
                // Gán danh sách mới vào đối tượng book @ModelAttribute
                book.setBookAuthors(newBookAuthors);
            } else {
                book.setBookAuthors(new ArrayList<>());
            }
            // LƯU ĐÚNG CHỖ VÀ ĐÚNG LẦN: Lưu đối tượng Book đã được gán bookAuthors
            bookService.saveBook(book); // Lệnh save cuối cùng

            return "redirect:/books"; // CHUYỂN HƯỚNG THÀNH CÔNG

        } catch (Exception e) {
            // --- XỬ LÝ LỖI (TRẢ VỀ FORM) ---
            e.printStackTrace();

            // Đảm bảo đối tượng book và danh sách authors được đưa vào Model
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("errorMessage", "Error saving Book: " + e.getMessage());

            return "books/book-form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}