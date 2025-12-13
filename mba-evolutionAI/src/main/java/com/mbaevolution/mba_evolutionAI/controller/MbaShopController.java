package com.mbaevolution.mba_evolutionAI.controller;

import com.mbaevolution.mba_evolutionAI.entity.MbaProduct;
import com.mbaevolution.mba_evolutionAI.repository.MbaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest; // Cần import

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MbaShopController {

    @Autowired
    private MbaProductRepository productRepository;

    @GetMapping("/shop")
    public String showShopPage(
            Model model,
            @RequestParam(required = false, defaultValue = "All") String category,
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(required = false, defaultValue = "100000000") Double maxPrice,
            HttpServletRequest request // THÊM HttpServletRequest request
    ) {

        // THÊM DÒNG NÀY ĐẦU TIÊN
        model.addAttribute("requestURI", request.getRequestURI());

        List<MbaProduct> allProducts = productRepository.findAll();

        List<MbaProduct> filteredProducts = allProducts.stream()
                .filter(p -> "All".equals(category) || category.equals(p.getMbaCategory()))
                .filter(p -> p.getMbaPrice() != null)
                .filter(p -> p.getMbaPrice() <= maxPrice)
                .filter(p -> {
                    String searchLower = query.toLowerCase();
                    return p.getMbaName().toLowerCase().contains(searchLower) ||
                            p.getMbaBrand().toLowerCase().contains(searchLower);
                })
                .collect(Collectors.toList());

        filteredProducts.sort(Comparator.comparing(MbaProduct::getMbaProductId).reversed());

        List<String> categories = allProducts.stream()
                .map(MbaProduct::getMbaCategory)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchQuery", query);
        model.addAttribute("priceRange", maxPrice);

        return "mba-shop";
    }
}