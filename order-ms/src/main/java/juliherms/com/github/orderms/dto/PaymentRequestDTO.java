package juliherms.com.github.orderms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Class responsible to represent PaymentRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    private Long orderId;
    private Integer userId;
    private BigDecimal amount;
}
