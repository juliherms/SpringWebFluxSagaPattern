package juliherms.com.github.orderms.service;

import juliherms.com.github.orderms.dto.OrderRequestDTO;
import juliherms.com.github.orderms.events.OrderEvent;
import juliherms.com.github.orderms.model.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

/**
 * Class responsible to publish event with message broker
 */
@Service
public class OrderStatusPublisherService {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;


    /**
     * Responsible to emitter Order Event
     * @param orderRequestDTO
     * @param orderStatus
     */
    public void publishOrderEvent(OrderRequestDTO orderRequestDTO, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderRequestDTO,orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
