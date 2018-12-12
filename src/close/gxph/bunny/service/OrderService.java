package close.gxph.bunny.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import close.gxph.bunny.entity.Order;
import close.gxph.bunny.repository.OrderDao;
import close.gxph.core.common.util.CommonService;
import close.gxph.core.constant.Contants;

@Component
@Transactional
public class OrderService extends CommonService<Order>{
	
	@Autowired
	private OrderDao orderDao;

	/**
	 * 查询订单带分页
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param order
	 * @return
	 */
	public Page<Order> getOrders(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy, String order) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortBy,order);
		Specification<Order> spec = buildSpecification(searchParams);
		return orderDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 根据条件查询订单（不带分页）
	 * @param searchParams
	 * @return
	 */
	public List<Order> findOrderByCondition(Map<String,Object> searchParams){
		Specification<Order> spec=buildSpecification(searchParams);
		return orderDao.findAll(spec,new Sort(Direction.DESC,"odate"));
	}
	
	/**
	 * 支付订单
	 * @param orderId
	 */
	public void payOrder(String orderId){
		Order order=orderDao.findOne(orderId);
		
	}
	
	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	public Order addOrder(Order order) {
		order.setIsuse(Contants.STATE_NO);
		order.setStep(Contants.ORDER_STATUS_ON);
		order.setOdate(new Timestamp(System.currentTimeMillis()));
		return orderDao.saveAndFlush(order);
	}
	
	/**
	 * 修改订单
	 * @param order
	 */
	public void updateOrder(Order order) {
		orderDao.save(order);
	}

	/**
	 * 获取一个订单
	 * @param id
	 * @return
	 */
	public Order getOrderById(String id) {
		return orderDao.getOne(id);
	}

	/**
	 * 删除订单
	 * @param id
	 */
	public void deleteOrderById(String id) {
		orderDao.delete(id);
	}
	
	/**
	 * 修改订单状态
	 * @param orderId 订单id
	 * @param step  状态
	 */
	public void updateOrderState(String orderId,String step){
		Order order=orderDao.findOne(orderId);
		order.setStep(step);
		orderDao.saveAndFlush(order);
	}
}
