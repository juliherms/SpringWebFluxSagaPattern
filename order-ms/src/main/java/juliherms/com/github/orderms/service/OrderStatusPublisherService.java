package juliherms.com.github.orderms.service;

import juliherms.com.github.orderms.dto.OrderRequestDTO;
import juliherms.com.github.orderms.events.OrderEvent;
import juliherms.com.github.orderms.model.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisherService {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequestDTO orderRequestDTO, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderRequestDTO,orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
