package com.mbaevolution.mba_evolutionAI.repository;

import com.mbaevolution.mba_evolutionAI.dto.MbaDailyRevenueDTO;
import com.mbaevolution.mba_evolutionAI.entity.MbaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MbaOrderRepository extends JpaRepository<MbaOrder, Long> {

    // Tìm đơn hàng của user cụ thể (Dùng cho lịch sử mua hàng)
    List<MbaOrder> findByUser_MbaUserIdOrderByMbaOrderDateDesc(Long userId);

    // Tìm đơn hàng theo Username (Dùng cho search Admin nếu cần)
    List<MbaOrder> findByUser_MbaUsername(String username);

    // --- HÀM MỚI: Thống kê doanh thu theo ngày ---
    // DATE(o.mbaOrderDate): Cắt bỏ giờ phút, chỉ lấy ngày để nhóm
    // SUM: Cộng tổng tiền
    // COUNT: Đếm số đơn
    // new ...MbaDailyRevenueDTO(...): Đổ kết quả vào DTO ngay lập tức
    @Query("SELECT new com.mbaevolution.mba_evolutionAI.dto.MbaDailyRevenueDTO(" +
            "DATE(o.mbaOrderDate), SUM(o.mbaTotalAmount), COUNT(o)) " +
            "FROM MbaOrder o " +
            "WHERE o.mbaStatus = 'COMPLETED' " +
            "GROUP BY DATE(o.mbaOrderDate) " +
            "ORDER BY DATE(o.mbaOrderDate) DESC")
    List<MbaDailyRevenueDTO> getDailyRevenue();
}