package juliherms.com.github.paymentms.repository;

import juliherms.com.github.paymentms.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance,Long> {

}
