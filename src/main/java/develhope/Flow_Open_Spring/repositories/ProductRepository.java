package develhope.Flow_Open_Spring.repositories;

import develhope.Flow_Open_Spring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
