package juliherms.com.github.orderms.repository;

import juliherms.com.github.orderms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class responsible to access Order entity
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
