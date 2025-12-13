package com.mbaevolution.mba_evolutionAI.controller;

import com.mbaevolution.mba_evolutionAI.entity.MbaOrder;
import com.mbaevolution.mba_evolutionAI.repository.MbaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/mba-admin/orders")
public class MbaAdminOrderController {

    @Autowired
    private MbaOrderRepository orderRepository;

    // 1. Hiển thị danh sách đơn hàng
    @GetMapping
    public String listOrders(Model model) {
        // Lấy tất cả đơn, sắp xếp mới nhất lên đầu
        List<MbaOrder> orders = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "mbaOrderDate"));
        model.addAttribute("orders", orders);
        return "mba-admin-orders"; // Trả về file HTML (sẽ tạo ở bước 2)
    }

    // 2. Cập nhật trạng thái đơn (Ví dụ: Từ Chờ -> Đang giao -> Hoàn thành)
    @GetMapping("/update-status/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        MbaOrder order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setMbaStatus(status);
            orderRepository.save(order);
        }
        return "redirect:/mba-admin/orders";
    }
}