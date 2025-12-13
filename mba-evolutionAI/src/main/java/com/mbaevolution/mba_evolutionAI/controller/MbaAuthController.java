package com.mbaevolution.mba_evolutionAI.controller;

import com.mbaevolution.mba_evolutionAI.entity.MbaUser;
import com.mbaevolution.mba_evolutionAI.repository.MbaUserRepository;
import com.mbaevolution.mba_evolutionAI.services.MbaUserService; // IMPORT SERVICE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Cần thiết cho showLoginForm
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest; // Cần thiết cho Header
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class MbaAuthController {

    @Autowired
    private MbaUserRepository userRepository;

    @Autowired // <--- SỬA LỖI: TIÊM SERVICE
    private MbaUserService userService;

    // Hàm hiển thị form login (Chuyển từ ViewController sang đây để đơn giản)
    @GetMapping("/login")
    public String showLoginForm(Model model, HttpServletRequest request) {
        // Bắt buộc cho Header Fragment
        model.addAttribute("requestURI", request.getRequestURI());
        return "mba-login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println("Login attempt: " + username + " / " + password);
        // 1. GỌI SERVICE để xác thực
        Optional<MbaUser> userOptional = userService.authenticate(username, password);

        if (userOptional.isPresent()) {
            MbaUser user = userOptional.get();
            session.setAttribute("currentUser", user);

            // 2. Kiểm tra role bằng thuộc tính MỚI mbaRole
            if ("ADMIN".equals(user.getMbaRole())) {
                return "redirect:/mba-admin";
            } else {
                return "redirect:/";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String fullName,
            @RequestParam String email
    ) {
        // Kiểm tra user đã tồn tại (dùng Repository trực tiếp)
        if (userRepository.findByMbaUsername(username).isPresent()) {
            return "redirect:/register?error";
        }

        try {
            MbaUser newUser = new MbaUser();
            newUser.setMbaUsername(username);

            // SỬA LỖI ÁNH XẠ: Dùng Setter chính xác (setMbaPassword, setMbaFullName, setMbaEmail)
            newUser.setMbaUserPassword(password);
            newUser.setMbaUserFullName(fullName);
            newUser.setMbaUserEmail(email);

            newUser.setMbaRole("USER");

            userRepository.save(newUser);

            return "redirect:/login?success";
        } catch (Exception e) {
            return "redirect:/register?error";
        }
    }
}