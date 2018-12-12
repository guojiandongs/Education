package close.gxph.bunny.repository;


import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import close.gxph.bunny.entity.Product;
import close.gxph.bunny.entity.Validate;

public interface ProductHessianDao{
	Page<Product> getProductList(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType);
	void deleteProduct(String id);
	void updatevalidate(String id,String status);
	String addProduct(Product product);
	Product getProduct(String id);
	String alterProduct(Product product);
	void validate(Product product,Validate validate);
}
