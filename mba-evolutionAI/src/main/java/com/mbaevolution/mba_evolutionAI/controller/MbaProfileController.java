package com.mbaevolution.mba_evolutionAI.controller;

import com.mbaevolution.mba_evolutionAI.entity.MbaUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class MbaProfileController {

    @GetMapping
    public String showUserProfile(HttpSession session, Model model) {
        MbaUser currentUser = (MbaUser) session.getAttribute("currentUser");

        if (currentUser == null) {
            return "redirect:/mba-login";
        }

        model.addAttribute("user", currentUser);

        return "mba-profile";
    }
}