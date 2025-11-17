package K23CNT1_Mba_Day05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin") // Tất cả các request sẽ bắt đầu bằng /admin
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Admin::Dashboard");
        // Trả về file dashboard.html (sẽ được tạo ở bước sau)
        return "admin/dashboard";
    }

    // Thêm các phương thức cho CRUD ở bước tiếp theo...
    // Xử lý request: /admin/products (Hiển thị danh sách)
    @GetMapping("/products")
    public String listProducts(Model model) {
        // [Tạm thời] Tạo dữ liệu giả cho danh sách sản phẩm
        // List<Product> products = productService.findAll();
        // model.addAttribute("products", products);

        return "admin/product_list";
    }

    // Xử lý request: /admin/products/add (Hiển thị form thêm mới)
    @GetMapping("/products/add")
    public String addProductForm(Model model) {
        // [Tạm thời] Tạo một đối tượng trống để Form Binding
        // model.addAttribute("productDto", new ProductDto());

        return "admin/product_add";
    }

    // Xử lý request: /admin/products/edit/{id} (Hiển thị form chỉnh sửa)
    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        // [Tạm thời] Lấy dữ liệu sản phẩm theo ID để binding vào form
        // ProductDto productDto = productService.findById(id);
        // model.addAttribute("productDto", productDto);

        return "admin/product_edit";
    }
}