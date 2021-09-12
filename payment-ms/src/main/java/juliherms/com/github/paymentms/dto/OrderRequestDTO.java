package juliherms.com.github.paymentms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private Long userId;
    private Long productId;
    private BigDecimal amount;
    private Long orderId;
}
