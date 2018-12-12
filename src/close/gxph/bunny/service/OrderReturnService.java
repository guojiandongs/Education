package close.gxph.bunny.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.bunny.entity.Order;
import close.gxph.bunny.entity.OrderReturn;
import close.gxph.bunny.repository.OrderReturnDao;
import close.gxph.core.common.util.CommonService;

@Component
@Transactional
public class OrderReturnService extends CommonService<OrderReturn>{
	
	@Autowired
	private OrderReturnDao orderReturnDao;

	/**
	 * 添加退款
	 * @param orderReturn
	 * @return
	 */
	public OrderReturn addOrder(OrderReturn orderReturn) {
		return orderReturnDao.save(orderReturn);
	}
	/**
	 * 根据id获取退票
	 * @param id
	 * @return
	 */
	public OrderReturn getOrderReturnById(String id){
		return orderReturnDao.findOne(id);
	}
	/**
	 * 修改退票
	 * @param orderReturn
	 */
	public void alterOrderReturn(OrderReturn orderReturn){
		 orderReturnDao.save(orderReturn);
	}
	/***
	 * 按条件查询退货记录
	 * @param searchParams
	 * @return
	 */
	public Page<OrderReturn> getOrderreturns(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy, String order) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortBy,order);
		Specification<OrderReturn> spec = buildSpecification(searchParams);
		return orderReturnDao.findAll(spec, pageRequest);
	}
}
