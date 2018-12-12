package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.OrderReturn;


public interface OrderReturnHessianDao{
	Page<OrderReturn> getOrderreturns(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy, String order);
	OrderReturn getOrderReturnById(String id);
	void alterOrderReturn(OrderReturn orderReturn);
}
