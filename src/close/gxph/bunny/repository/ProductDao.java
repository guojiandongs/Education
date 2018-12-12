package close.gxph.bunny.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import close.gxph.bunny.entity.Product;

public interface ProductDao extends JpaRepository<Product, String>,
		JpaSpecificationExecutor<Product> {
	
}
