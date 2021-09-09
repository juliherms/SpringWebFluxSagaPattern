package juliherms.com.github.orderms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    private Long orderId;
    private Integer userId;
    private BigDecimal amount;
}
