package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import close.gxph.bunny.entity.Rate;

public interface RateHessianDao{
	
	Page<Rate> getTatePage(Map<String, Object> searchParams, int pageNumber,
			int pageSize,String sortBy,String sortType);
	
	void deleteRate(String id);
		
	String addRate(Rate rate);
	
	Rate getRate(String id);
	
	String alterRate(Rate rate);
	
	List<Rate> listAll();
}
