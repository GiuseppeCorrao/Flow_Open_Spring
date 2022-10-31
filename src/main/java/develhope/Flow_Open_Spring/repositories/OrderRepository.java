package develhope.Flow_Open_Spring.repositories;

import develhope.Flow_Open_Spring.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderRepository extends JpaRepository<Order, Long> {

}
