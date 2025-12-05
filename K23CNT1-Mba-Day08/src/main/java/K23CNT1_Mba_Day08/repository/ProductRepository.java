package K23CNT1_Mba_Day08.repository;

import K23CNT1_Mba_Day08.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Fetch Join cho trang danh sách Product (Fix Lazy Loading)
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.productConfigs pc LEFT JOIN FETCH pc.configuration")
    List<Product> findAllWithConfigs();

    // Fetch Join khi tải Product theo ID (Fix Lazy Loading cho trang Edit)
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.productConfigs pc LEFT JOIN FETCH pc.configuration WHERE p.id = :id")
    Optional<Product> findByIdWithConfigs(@Param("id") Long id);
}