package K23CNT1_Mba_Day08.controller;

import K23CNT1_Mba_Day08.entity.Configuration;
import K23CNT1_Mba_Day08.entity.Product;
import K23CNT1_Mba_Day08.entity.ProductConfig;
import K23CNT1_Mba_Day08.service.ConfigurationService;
import K23CNT1_Mba_Day08.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Cần thiết cho Stream API

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ConfigurationService configurationService;
    // Không cần ProductConfigRepository

    private static final String UPLOAD_DIR = "src/main/resources/static/";
    private static final String UPLOAD_PathFile = "images/products/";

    // 1. READ ALL - Hiển thị danh sách sản phẩm
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/product-list";
    }

    // 2. CREATE (GET) - Hiển thị form thêm mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        // Lấy tất cả loại cấu hình (Ram, CPU, v.v.)
        model.addAttribute("configurations", configurationService.getAllConfigurations());
        return "products/product-form";
    }

    // 3. UPDATE (GET) - Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("configurations", configurationService.getAllConfigurations());
        return "products/product-form";
    }

    // 4. CREATE/UPDATE (POST) - Xử lý lưu sản phẩm và cấu hình
    @PostMapping("/new")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam(required = false) List<String> configIds,
                              @RequestParam(required = false) List<String> configValues,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Model model, RedirectAttributes redirectAttributes) {

        Product productToPersist = product;

        try {
            // --- 1. Xử lý Upload ảnh (Fix File Lock và Lỗi Ảnh) ---
            if(!imageFile.isEmpty()) {
                Path uploadPath = Paths.get(UPLOAD_DIR + UPLOAD_PathFile);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories (uploadPath);
                }
                String originalFilename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String fileExtension = originalFilename.substring (originalFilename.lastIndexOf("."));

                String newFileName = product.getCode() + fileExtension;
                Path filePath = uploadPath.resolve(newFileName);

                try (var inputStream = imageFile.getInputStream()) {
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                // Lưu đường dẫn công khai
                product.setImgUrl("/" + UPLOAD_PathFile + newFileName);
            }

            // --- 2. Xử lý Quản lý Bộ sưu tập ProductConfig (Fix Orphan Deletion) ---

            // Nếu là UPDATE, phải tải lại Entity đã tồn tại
            if (product.getId() != null) {
                Product existingProduct = productService.getProductById(product.getId());

                if (existingProduct != null) {
                    // CẬP NHẬT các trường cơ bản từ form
                    existingProduct.setName(product.getName());
                    existingProduct.setCode(product.getCode());
                    existingProduct.setQuantity(product.getQuantity());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setIsActive(product.getIsActive());
                    if (product.getImgUrl() != null) {
                        existingProduct.setImgUrl(product.getImgUrl());
                    }

                    // Xóa các liên kết ProductConfig cũ (Kích hoạt Orphan Removal)
                    if (existingProduct.getProductConfigs() != null) {
                        existingProduct.getProductConfigs().clear();
                    }
                    productToPersist = existingProduct;
                }
            } else {
                productToPersist = product; // Nếu là CREATE, dùng đối tượng mới
            }

            // 3. Tạo và Gán các đối tượng ProductConfig mới
            if (configIds != null && !configIds.isEmpty()) {

                // Chuẩn bị danh sách cấu hình và giá trị
                List<Long> configLongIds = configIds.stream().map(Long::valueOf).toList();
                List<Configuration> configs = configurationService.findAllById(configLongIds);
                List<ProductConfig> newProductConfigs = new ArrayList<>();

                for (int i = 0; i < configIds.size(); i++) {
                    Long configId = configLongIds.get(i);
                    String value = (i < configValues.size()) ? configValues.get(i) : null;

                    Configuration config = configs.stream()
                            .filter(c -> c.getId().equals(configId))
                            .findFirst().orElse(null);

                    if (config != null && value != null && !value.isEmpty()) {
                        ProductConfig pc = new ProductConfig();
                        pc.setProduct(productToPersist);
                        pc.setConfiguration(config);
                        pc.setValue(value);
                        newProductConfigs.add(pc);
                    }
                }

                // Gán danh sách mới vào bộ sưu tập của Product
                productToPersist.getProductConfigs().addAll(newProductConfigs);
            } else {
                productToPersist.setProductConfigs(new ArrayList<>());
            }

            // LƯU LẦN CUỐI (Hibernate xử lý tất cả)
            productService.saveProduct(productToPersist);

            redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully!");
            return "redirect:/products";

        } catch (Exception e) {
            e.printStackTrace();

            // Xử lý lỗi (Trả về form với dữ liệu đã nhập)
            model.addAttribute("product", product);
            model.addAttribute("configurations", configurationService.getAllConfigurations());
            model.addAttribute("errorMessage", "Error saving Product: " + e.getMessage());
            return "products/product-form";
        }
    }

    // 5. DELETE
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}