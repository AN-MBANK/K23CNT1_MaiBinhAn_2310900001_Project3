package K23CNT1_Mba_Day08.service;

import K23CNT1_Mba_Day08.entity.Product;
import K23CNT1_Mba_Day08.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        // Sử dụng Fetch Join để tránh Lazy Loading khi View truy cập ProductConfig
        return productRepository.findAllWithConfigs();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        // Sử dụng Fetch Join để tải Configuration khi cần thiết cho trang Edit
        return productRepository.findByIdWithConfigs(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}