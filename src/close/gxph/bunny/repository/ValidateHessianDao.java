package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import close.gxph.bunny.entity.Validate;

public interface ValidateHessianDao{
	Page<Validate> getValidates(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType);
	
}
