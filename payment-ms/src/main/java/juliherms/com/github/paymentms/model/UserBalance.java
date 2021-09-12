package juliherms.com.github.paymentms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "TB_USER_BALANCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalance {

    @Id
    private Long userId;

    private BigDecimal price;
}
