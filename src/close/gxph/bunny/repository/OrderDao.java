package close.gxph.bunny.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import close.gxph.bunny.entity.Order;

public interface OrderDao extends JpaRepository<Order, String>,
		JpaSpecificationExecutor<Order> {
	@Modifying
	public List<Order> findByGuestid(String userid);
}
