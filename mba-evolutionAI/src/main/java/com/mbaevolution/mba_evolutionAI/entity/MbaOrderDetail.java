package com.mbaevolution.mba_evolutionAI.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mba_order_details")
public class MbaOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbaDetailId;

    // Thuộc về đơn hàng nào?
    @ManyToOne
    @JoinColumn(name = "mba_order_id")
    private MbaOrder order;

    // Mua sản phẩm nào?
    @ManyToOne
    @JoinColumn(name = "mba_product_id")
    private MbaProduct product;

    private int mbaQuantity; // Số lượng
    private Double mbaPrice; // Giá tiền TẠI THỜI ĐIỂM MUA (Quan trọng để không bị đổi khi giá sản phẩm thay đổi)
}