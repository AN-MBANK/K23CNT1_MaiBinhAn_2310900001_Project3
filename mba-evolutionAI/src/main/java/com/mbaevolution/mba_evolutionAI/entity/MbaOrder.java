package com.mbaevolution.mba_evolutionAI.entity;

import jakarta.persistence.*;
import lombok.Data; // <-- Quan trọng: Phải có dòng này để tự tạo Getter/Setter
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data // <-- Quan trọng: Lombok sẽ tự tạo getMbaFullName(), setMbaFullName()...
@Table(name = "mba_orders")
public class MbaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbaOrderId;

    @ManyToOne
    @JoinColumn(name = "mba_user_id")
    private MbaUser user;

    // --- THÔNG TIN KHÁCH HÀNG ---
    // Lưu ý: Tên biến là 'mbaFullName' để khớp với HTML
    @Column(name = "mba_customer_name")
    private String mbaFullName;

    @Column(name = "mba_customer_phone")
    private String mbaPhone;

    @Column(name = "mba_address")
    private String mbaAddress;

    private String mbaNote;

    // --- THÔNG TIN ĐƠN HÀNG ---
    @Column(name = "mba_total_price")
    private Double mbaTotalAmount; // Tên biến là mbaTotalAmount

    @Column(name = "mba_status")
    private String mbaStatus = "PENDING"; // Mặc định là PENDING

    @Column(name = "mba_order_date")
    private LocalDateTime mbaOrderDate = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<MbaOrderDetail> orderDetails;

    // --- KHẮC PHỤC LỖI TRONG ẢNH ---
    // Nếu code cũ của ông đang lỡ gọi setMbaTotalPrice, ông sửa nó thành setMbaTotalAmount
    // Hoặc nếu muốn chắc ăn, ông có thể thêm getter/setter thủ công (nếu Lombok lỗi)
}