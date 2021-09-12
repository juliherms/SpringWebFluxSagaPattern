package juliherms.com.github.paymentms.service;

import juliherms.com.github.paymentms.dto.OrderRequestDTO;
import juliherms.com.github.paymentms.dto.PaymentRequestDTO;
import juliherms.com.github.paymentms.events.OrderEvent;
import juliherms.com.github.paymentms.events.PaymentEvent;
import juliherms.com.github.paymentms.model.UserBalance;
import juliherms.com.github.paymentms.model.UserTransaction;
import juliherms.com.github.paymentms.model.enums.PaymentStatus;
import juliherms.com.github.paymentms.repository.UserBalanceRepository;
import juliherms.com.github.paymentms.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDB() {
        userBalanceRepository.saveAll(
                Stream.of(
                        new UserBalance(1L, BigDecimal.TEN),
                        new UserBalance(2L, BigDecimal.TEN),
                        new UserBalance(3L, BigDecimal.TEN),
                        new UserBalance(4L, BigDecimal.TEN),
                        new UserBalance(5L, BigDecimal.TEN))
                        .collect(Collectors.toList()));
    }

    /**
     * get the user id
     * check the balance availability
     * if balance sufficient -> Payment completed and deduct amount from DB
     * if payment not sufficient -> cancel order event and update the amount in DB
     **/
    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {

        //getting order
        OrderRequestDTO orderRequestDTO = orderEvent.getOrderRequestDTO();

        //create payment request
        PaymentRequestDTO paymentRequestDto = new PaymentRequestDTO(orderRequestDTO.getOrderId(),
                orderRequestDTO.getUserId(), orderRequestDTO.getAmount());

        return userBalanceRepository.findById(orderRequestDTO.getUserId())
                .filter(ub -> ub.getPrice().compareTo(orderRequestDTO.getAmount()) > 0 ) // compare
                .map(ub -> {
                    ub.setPrice(ub.getPrice().subtract(orderRequestDTO.getAmount())); // subtract value
                    userTransactionRepository.save(new UserTransaction(orderRequestDTO.getOrderId(), orderRequestDTO.getUserId(), orderRequestDTO.getAmount()));
                    return new PaymentEvent(paymentRequestDto, PaymentStatus.COMPLETED);
                }).orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.FAILED));

    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {

        userTransactionRepository.findById(orderEvent.getOrderRequestDTO().getOrderId())
                .ifPresent(ut->{
                    userTransactionRepository.delete(ut);
                    userTransactionRepository.findById(ut.getUserId())
                            .ifPresent(ub -> ub.setAmount(ub.getAmount().add(ut.getAmount())));
                });
    }
}
