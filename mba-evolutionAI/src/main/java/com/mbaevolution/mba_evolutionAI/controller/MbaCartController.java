package com.mbaevolution.mba_evolutionAI.controller;

import com.mbaevolution.mba_evolutionAI.entity.MbaOrder;
import com.mbaevolution.mba_evolutionAI.entity.MbaProduct;
import com.mbaevolution.mba_evolutionAI.entity.MbaUser;
import com.mbaevolution.mba_evolutionAI.repository.MbaOrderRepository;
import com.mbaevolution.mba_evolutionAI.repository.MbaProductRepository;
import com.mbaevolution.mba_evolutionAI.services.MbaOrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("cart")
public class MbaCartController {

    @Autowired
    private MbaProductRepository productRepository;
    @Autowired
    private MbaOrderService orderService;
    @Autowired
    private MbaOrderRepository orderRepository;

    // Khởi tạo giỏ hàng nếu chưa tồn tại
    @ModelAttribute("cart")
    public Map<Long, Integer> cart() {
        return new HashMap<>();
    }

    // API AJAX: Thêm sản phẩm vào giỏ (Dùng cho ProductDetail.html)
    @PostMapping("/api/cart/add/{productId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCartApi(
            @PathVariable Long productId,
            @ModelAttribute("cart") Map<Long, Integer> cart
    ) {
        MbaProduct product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Sản phẩm không tồn tại."));
        }

        int newQuantity = cart.getOrDefault(productId, 0) + 1;
        cart.put(productId, newQuantity);

        int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();

        return ResponseEntity.ok(Map.of("success", true, "message", "Đã thêm " + product.getMbaName() + " vào giỏ!", "totalItems", totalItems));
    }

    // VIEW: Trang Giỏ hàng
    @GetMapping("/cart")
    public String showCart(Model model, @ModelAttribute("cart") Map<Long, Integer> cart) {
        List<MbaProduct> productsInCart = productRepository.findAllById(cart.keySet());

        Map<MbaProduct, Integer> detailedCart = productsInCart.stream()
                .collect(Collectors.toMap(p -> p, p -> cart.get(p.getMbaProductId())));

        double totalPrice = detailedCart.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getMbaPrice() * entry.getValue())
                .sum();

        model.addAttribute("detailedCart", detailedCart);
        model.addAttribute("totalPrice", totalPrice);

        return "mba-cart";
    }

    // POST: Cập nhật số lượng hoặc Xóa sản phẩm
    @PostMapping("/cart/update")
    public String updateCart(
            @RequestParam Long productId,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) String action,
            @ModelAttribute("cart") Map<Long, Integer> cart
    ) {
        if ("remove".equals(action) || (quantity != null && quantity <= 0)) {
            cart.remove(productId);
        } else if (quantity != null && quantity > 0) {
            cart.put(productId, quantity);
        }
        return "redirect:/cart";
    }

    // VIEW: Bắt đầu Thanh toán (Hiển thị form CheckoutModal)
    @GetMapping("/checkout")
    public String startCheckout(Model model, HttpSession session, @ModelAttribute("cart") Map<Long, Integer> cart) {
        if (cart.isEmpty()) {
            return "redirect:/cart";
        }

        MbaUser currentUser = (MbaUser) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);

        List<MbaProduct> productsInCart = productRepository.findAllById(cart.keySet());
        double totalPrice = productsInCart.stream()
                .mapToDouble(p -> p.getMbaPrice() * cart.get(p.getMbaProductId()))
                .sum();

        model.addAttribute("totalPrice", totalPrice);

        return "mba-checkout";
    }

    // POST: Hoàn tất Đặt hàng
    @PostMapping("/checkout/confirm")
    public String confirmCheckout(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam Double totalPrice,
            HttpSession session,
            @ModelAttribute("cart") Map<Long, Integer> cart,
            RedirectAttributes redirectAttributes
    ) {
        if (cart.isEmpty()) {
            return "redirect:/cart";
        }

        MbaUser currentUser = (MbaUser) session.getAttribute("currentUser");

        MbaOrder newOrder = new MbaOrder();
        newOrder.setMbaFullName(name);
        newOrder.setMbaPhone(phone);
        newOrder.setMbaAddress(address);
        newOrder.setMbaTotalAmount(totalPrice);
        newOrder.setMbaFullName(currentUser != null ? currentUser.getMbaUsername() : "GUEST");
        newOrder.setMbaOrderDate(LocalDateTime.now());
        newOrder.setMbaStatus("PENDING");

        try {
            orderRepository.save(newOrder);

            // Xóa giỏ hàng sau khi đặt thành công
            cart.clear();

            redirectAttributes.addFlashAttribute("checkoutSuccess", true);

            if (currentUser != null) {
                return "redirect:/user-orders";
            }
            return "redirect:/"; // Về trang chủ nếu là khách

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("checkoutError", true);
            return "redirect:/checkout";
        }
    }
}