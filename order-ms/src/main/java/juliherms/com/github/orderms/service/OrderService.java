package juliherms.com.github.orderms.service;

import juliherms.com.github.orderms.dto.OrderRequestDTO;
import juliherms.com.github.orderms.model.Order;
import juliherms.com.github.orderms.model.enums.OrderStatus;
import juliherms.com.github.orderms.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class responsible to represents business logic from Order
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private OrderStatusPublisherService orderStatusPublisherService;

    /**
     * Responsible to create order
     * @param orderRequestDto
     * @return
     */
    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDto) {

        Order order = repo.save(convertDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(order.getId());

        //send to kakfa event with status CREATED
        orderStatusPublisherService.publishOrderEvent(orderRequestDto, OrderStatus.CREATED);
        return order;
    }

    /**
     * Responsible to list all orders
     * @return
     */
    public List<Order> getAllOrders(){
        return repo.findAll();
    }

    /**
     * Responsible to convert DTO to Entity
     * @param dto
     * @return
     */
    private Order convertDtoToEntity(OrderRequestDTO dto) {
        Order order = new Order();
        order.setProductId(dto.getProductId());
        order.setUserId(dto.getUserId());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setPrice(dto.getAmount());
        return order;
    }
}
