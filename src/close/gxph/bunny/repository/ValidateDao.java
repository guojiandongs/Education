package close.gxph.bunny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import close.gxph.bunny.entity.Validate;

public interface ValidateDao extends JpaRepository<Validate, String>,
		JpaSpecificationExecutor<Validate> {
}
