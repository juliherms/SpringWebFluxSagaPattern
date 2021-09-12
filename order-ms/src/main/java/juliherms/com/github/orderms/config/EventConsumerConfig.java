package juliherms.com.github.orderms.config;

import juliherms.com.github.orderms.events.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * Class responsible to implements consumer for payments for order
 */
@Configuration
public class EventConsumerConfig {

    @Autowired
    private OrderStatusUpdateHandler handler;

    /**
     * Method responsible to listen payment-event
     * @return
     */
    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        //listen payment-event-topic
        //will check payment status
        //if payment status completed -> complete the order
        //if payment status failed -> cancel the order
        return (payment)-> handler.updateOrder(payment.getPaymentRequestDTO().getOrderId(),
                po->{
                    po.setPaymentStatus(payment.getPaymentStatus());
                });
    }
}
