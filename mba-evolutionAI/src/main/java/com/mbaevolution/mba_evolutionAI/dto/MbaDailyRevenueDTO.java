package com.mbaevolution.mba_evolutionAI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MbaDailyRevenueDTO {
    private LocalDate mbaDate;        // Ngày
    private Double mbaTotalAmount;    // Tổng tiền bán được trong ngày
    private Long mbaOrderCount;
    public MbaDailyRevenueDTO(Date date, Double totalAmount, Long orderCount) {
        this.mbaDate = (date != null) ? date.toLocalDate() : null;
        this.mbaTotalAmount = (totalAmount != null) ? totalAmount : 0.0;
        this.mbaOrderCount = (orderCount != null) ? orderCount : 0L;
    }// Tổng số đơn hàng trong ngày
}