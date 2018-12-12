package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import close.gxph.bunny.entity.Order;

public interface OrderHessianDao{
	Page<Order> getOrders(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy, String order);
	Order getOrderById(String id);
	void deleteOrderById(String id);
	void updateOrder(Order order);
	List<Order> findOrderByCondition(Map<String,Object> searchParams);
}
