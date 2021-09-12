package juliherms.com.github.paymentms.service;

import juliherms.com.github.paymentms.model.UserBalance;
import juliherms.com.github.paymentms.repository.UserBalanceRepository;
import juliherms.com.github.paymentms.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                        new UserBalance(101L, BigDecimal.TEN),
                        new UserBalance(102L, BigDecimal.TEN),
                        new UserBalance(103L, BigDecimal.TEN),
                        new UserBalance(104L, BigDecimal.TEN),
                        new UserBalance(105L, BigDecimal.TEN))
                        .collect(Collectors.toList()));
    }
}
