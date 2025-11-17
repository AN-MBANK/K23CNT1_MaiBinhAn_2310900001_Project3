package K23CNT1_Mba_Day07.service;

import K23CNT1_Mba_Day07.entity.Product;
import K23CNT1_Mba_Day07.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Đọc toàn bộ dữ liệu bảng Product
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Đọc dữ liệu bằng Product theo id
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    // Cập nhật: create / update
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Xóa product theo id
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}