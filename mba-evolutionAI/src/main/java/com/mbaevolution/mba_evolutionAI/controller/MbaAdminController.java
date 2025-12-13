package com.mbaevolution.mba_evolutionAI.controller;

import com.mbaevolution.mba_evolutionAI.entity.MbaOrder;
import com.mbaevolution.mba_evolutionAI.entity.MbaProduct;
import com.mbaevolution.mba_evolutionAI.entity.MbaUser;
import com.mbaevolution.mba_evolutionAI.repository.MbaOrderRepository;
import com.mbaevolution.mba_evolutionAI.repository.MbaProductRepository;
import com.mbaevolution.mba_evolutionAI.repository.MbaUserRepository; // <-- MỚI: Cần cái này để đếm User
import com.mbaevolution.mba_evolutionAI.services.MbaOrderService;
import com.mbaevolution.mba_evolutionAI.services.MbaProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort; // <-- MỚI: Để sắp xếp nhanh hơn
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mba-admin")
public class MbaAdminController {

    @Autowired
    private MbaOrderRepository orderRepository;

    @Autowired
    private MbaProductRepository productRepository;

    @Autowired
    private MbaUserRepository userRepository; // <-- MỚI: Inject Repository User

    @Autowired
    private MbaProductService productService;

    @Autowired
    private MbaOrderService orderService;

    @GetMapping
    public String showAdminDashboard(
            HttpSession session,
            Model model,
            @RequestParam(required = false) Long editId,
            HttpServletRequest request
    ) {
        // 1. KIỂM TRA QUYỀN ADMIN (Giữ nguyên)
        MbaUser currentUser = (MbaUser) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getMbaRole())) {
            return "redirect:/login";
        }

        // 2. KHẮC PHỤC LỖI MENU ACTIVE (Giữ nguyên của ông)
        model.addAttribute("requestURI", request.getRequestURI());

        // --- [PHẦN MỚI] TÍNH TOÁN SỐ LIỆU CHO 4 CÁI Ô THỐNG KÊ ---
        long productCount = productRepository.count();
        long userCount = userRepository.count();
        long orderCount = orderRepository.count();

        List<MbaOrder> allOrders = orderRepository.findAll();
        // Tính tổng tiền (Nếu đơn chưa có tiền thì tính là 0)
        double totalRevenue = allOrders.stream()
                .mapToDouble(order -> order.getMbaTotalAmount() != null ? order.getMbaTotalAmount() : 0)
                .sum();

        // Gửi số liệu thống kê sang HTML
        model.addAttribute("productCount", productCount);
        model.addAttribute("userCount", userCount);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("totalRevenue", totalRevenue);
        // -----------------------------------------------------------

        // 3. LẤY DANH SÁCH ĐƠN HÀNG (Sắp xếp mới nhất lên đầu)
        List<MbaOrder> orders = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "mbaOrderDate"));

        // 4. LẤY DANH SÁCH SẢN PHẨM (Sắp xếp mới nhất lên đầu)
        List<MbaProduct> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "mbaProductId"));

        // === [PHẦN SỬA ĐỔI QUAN TRỌNG Ở ĐÂY] ===
        MbaProduct productForm;
        if (editId != null) {
            // Nếu có editId -> Lấy sản phẩm từ DB bỏ vào form
            productForm = productRepository.findById(editId).orElse(new MbaProduct());
            model.addAttribute("isModalOpen", true); // Mở modal ngay
        } else {
            // Nếu không -> Tạo mới bỏ vào form
            productForm = new MbaProduct();
            model.addAttribute("isModalOpen", false);
        }
        // Gửi duy nhất biến "productForm" sang View
        model.addAttribute("productForm", productForm);
        // ========================================

        model.addAttribute("orders", orders);
        model.addAttribute("products", products);

        return "mba-admin";
    }

    // --- CÁC HÀM XỬ LÝ FORM SẢN PHẨM (GIỮ NGUYÊN KHÔNG ĐỔI) ---

    @PostMapping("/products/save")
    public String saveProduct(
            @ModelAttribute MbaProduct product,
            @RequestParam("imageFile") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {
        try {
            if (product.getMbaProductId() == null && file.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Vui lòng chọn ảnh cho sản phẩm mới.");
                return "redirect:/mba-admin";
            }

            if (!file.isEmpty()) {
                String imageUrl = productService.storeFile(file);
                product.setMbaImage(imageUrl);
            } else if (product.getMbaProductId() != null && product.getMbaImage() == null) {
                MbaProduct existingProduct = productRepository.findById(product.getMbaProductId()).orElse(null);
                if (existingProduct != null) {
                    product.setMbaImage(existingProduct.getMbaImage());
                }
            }

            // Xử lý giá null để tránh lỗi DB
            if (product.getMbaPrice() == null) product.setMbaPrice(0.0);
            if (product.getMbaOriginalPrice() == null) product.setMbaOriginalPrice(0.0);

            productRepository.save(product);
            redirectAttributes.addFlashAttribute("success", "Lưu sản phẩm thành công!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi lưu sản phẩm: " + e.getMessage());
        }

        return "redirect:/mba-admin";
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam Long productId) {
        productRepository.deleteById(productId);
        return "redirect:/mba-admin";
    }
}