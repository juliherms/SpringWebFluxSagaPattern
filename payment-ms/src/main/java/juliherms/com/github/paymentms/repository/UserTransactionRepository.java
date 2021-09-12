package juliherms.com.github.paymentms.repository;

import juliherms.com.github.paymentms.model.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Long> {

}
