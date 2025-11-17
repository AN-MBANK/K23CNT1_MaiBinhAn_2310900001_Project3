package K23CNT1_Mba_Day05.controller;
import K23CNT1_Mba_Day05.entity.Info; // Import Entity vừa tạo
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping // Ánh xạ request gốc
public class HomeController {

    // 1. Lab 05.1: Trang Index (http://localhost:8080/)
    @GetMapping
    public String index(){
        return "index"; // Trả về templates/index.html
    }

    // 2. Lab 05.1: Trang Profile (http://localhost:8080/profile)
    @GetMapping("/profile")
    public String profile(Model model){
        List<Info> profile = new ArrayList<>();
        // Tạo thông tin profile
        profile.add(new Info("Devmaster Academy", "dev", "contact@devmaster.edu.vn", "https://devmaster.edu.vn"));

        // Đưa profile vào model với tên "DevmasterProfile" [cite: 75, 284]
        model.addAttribute("DevmasterProfile", profile);
        return "profile"; // Trả về templates/profile.html
    }

    // 3. Lab 05.2: Trang Home (http://localhost:8080/home)
    @GetMapping("/home")
    public String home(Model model) {
        // Gửi title để Fragment head sử dụng [cite: 259]
        model.addAttribute("title", "Devmaster::Trang chủ" );
        return "home"; // Trả về templates/home.html
    }

    // 4. Lab 05.2: Trang About (http://localhost:8080/about)
    @GetMapping("/about")
    public String about(Model model) {
        return "about"; // Trả về templates/about.html
    }

    // 5. Lab 05.2: Trang Contact (http://localhost:8080/contact)
    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact"; // Trả về templates/contact.html
    }
}