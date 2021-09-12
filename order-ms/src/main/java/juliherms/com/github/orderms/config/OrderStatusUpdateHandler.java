package juliherms.com.github.orderms.config;

import juliherms.com.github.orderms.dto.OrderRequestDTO;
import juliherms.com.github.orderms.model.Order;
import juliherms.com.github.orderms.model.enums.OrderStatus;
import juliherms.com.github.orderms.model.enums.PaymentStatus;
import juliherms.com.github.orderms.repository.OrderRepository;
import juliherms.com.github.orderms.service.OrderStatusPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

/**
 * Class responsible to handler order status
 */
@Configuration
public class OrderStatusUpdateHandler {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderStatusPublisherService orderStatusPublisherService;

    @Transactional
    public void updateOrder(Long id, Consumer<Order> consumer) {
        repository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    /**
     * Method responsible to update order and emitter event to broker
     * @param order
     */
    private void updateOrder(Order order) {

        boolean isPaymentComplete = PaymentStatus.COMPLETED.equals(order.getPaymentStatus());
        OrderStatus orderStatus = isPaymentComplete ? OrderStatus.COMPLETED : OrderStatus.CANCELLED;
        order.setOrderStatus(orderStatus);

        if (!isPaymentComplete) {
            orderStatusPublisherService.publishOrderEvent(convertEntityToDto(order), orderStatus);
        }
    }

    public OrderRequestDTO convertEntityToDto(Order order) {

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();

        orderRequestDTO.setOrderId(order.getId());
        orderRequestDTO.setUserId(order.getUserId());
        orderRequestDTO.setAmount(order.getPrice());
        orderRequestDTO.setProductId(order.getProductId());

        return orderRequestDTO;
    }
}
