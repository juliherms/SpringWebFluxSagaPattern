package juliherms.com.github.paymentms.events;

import juliherms.com.github.paymentms.dto.OrderRequestDTO;
import juliherms.com.github.paymentms.model.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 *  Class responsible to represents model for Order Event
 */
@NoArgsConstructor
@Data
public class OrderEvent implements Event {

    private UUID eventId=UUID.randomUUID();
    private Date eventDate = new Date();
    private OrderRequestDTO orderRequestDTO;
    private OrderStatus orderStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public OrderEvent(OrderRequestDTO orderRequestDTO, OrderStatus orderStatus) {
        this.orderRequestDTO = orderRequestDTO;
        this.orderStatus = orderStatus;
    }
}
