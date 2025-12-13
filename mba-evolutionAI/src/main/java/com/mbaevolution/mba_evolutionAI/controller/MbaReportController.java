package com.mbaevolution.mba_evolutionAI.controller;

import com.mbaevolution.mba_evolutionAI.dto.MbaDailyRevenueDTO;
import com.mbaevolution.mba_evolutionAI.repository.MbaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mba-admin/reports")
public class MbaReportController {

    @Autowired
    private MbaOrderRepository orderRepository;

    @GetMapping
    public String showRevenueReport(Model model) {
        // 1. Lấy danh sách doanh thu theo ngày từ Database
        List<MbaDailyRevenueDTO> revenueList = orderRepository.getDailyRevenue();

        // 2. Tính tổng doanh thu toàn thời gian (Grand Total)
        // Dùng stream để cộng dồn cột mbaTotalAmount của tất cả các ngày
        double grandTotal = revenueList.stream()
                .mapToDouble(dto -> dto.getMbaTotalAmount() != null ? dto.getMbaTotalAmount() : 0.0)
                .sum();

        // 3. Gửi sang HTML
        model.addAttribute("revenueList", revenueList);
        model.addAttribute("grandTotal", grandTotal);

        return "mba-admin-reports";
    }
}