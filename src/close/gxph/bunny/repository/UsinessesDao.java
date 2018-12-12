package close.gxph.bunny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;

public interface UsinessesDao extends JpaRepository<Usinesses, String>,
		JpaSpecificationExecutor<Usinesses> {
	Usinesses findByUsername(String username);
}
