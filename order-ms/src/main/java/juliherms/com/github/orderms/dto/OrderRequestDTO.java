package juliherms.com.github.orderms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Class responsible to represent OrderRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private Integer userId;
    private Integer productId;
    private BigDecimal amount;
    private Long orderId;
}
