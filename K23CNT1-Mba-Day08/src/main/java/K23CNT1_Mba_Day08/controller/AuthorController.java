package K23CNT1_Mba_Day08.controller;

import K23CNT1_Mba_Day08.entity.Author;
import K23CNT1_Mba_Day08.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Hiển thị danh sách tác giả
    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors/author-list";
    }
// Trong AuthorController.java

    // Sửa phương thức showCreateForm (Dành cho GET /authors/new)
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // Đảm bảo đối tượng "author" luôn được thêm vào Model
        model.addAttribute("author", new Author());
        return "authors/author-form";
    }

    // Sửa phương thức saveAuthor (Dành cho POST /authors/new)
    @PostMapping("/new")
    public String saveAuthor(@ModelAttribute Author author, Model model) { // THÊM Model ở đây
        try {
            authorService.saveAuthor(author);
            return "redirect:/authors";
        } catch (Exception e) {
            // Nếu có lỗi (ví dụ: lỗi SQL), chúng ta cần trả về form
            // và đảm bảo đối tượng 'author' vẫn ở trong Model để Thymeleaf xử lý
            model.addAttribute("author", author);
            // Thêm thông báo lỗi (tùy chọn)
            model.addAttribute("errorMessage", "Error saving Author: " + e.getMessage());
            return "authors/author-form";
        }
    }

    // Hiển thị form Sửa tác giả
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "authors/author-form";
    }

    // Xóa tác giả
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}