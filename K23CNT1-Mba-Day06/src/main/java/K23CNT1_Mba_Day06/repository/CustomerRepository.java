package K23CNT1_Mba_Day06.repository;

import K23CNT1_Mba_Day06.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}