package com.mbaevolution.mba_evolutionAI.controller;

import com.mbaevolution.mba_evolutionAI.entity.MbaProduct;
import com.mbaevolution.mba_evolutionAI.repository.MbaProductRepository;
import com.mbaevolution.mba_evolutionAI.entity.MbaUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest; // Cần import này!

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MbaHomeController {

    @Autowired
    private MbaProductRepository productRepository;

    @GetMapping("/")
    public String showHomePage(Model model, HttpSession session, HttpServletRequest request) { // Thêm HttpServletRequest

        // Thêm Request URI vào Model
        model.addAttribute("requestURI", request.getRequestURI());

        List<MbaProduct> allProducts = productRepository.findAll();

        List<MbaProduct> featuredProducts = allProducts.stream()
                .limit(4)
                .collect(Collectors.toList());

        MbaUser currentUser = (MbaUser) session.getAttribute("currentUser");

        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("currentUser", currentUser);

        return "mba-home";
    }
}