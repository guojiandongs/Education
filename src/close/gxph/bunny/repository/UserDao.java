package close.gxph.bunny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import close.gxph.bunny.entity.User;

public interface UserDao extends JpaRepository<User, String>,
		JpaSpecificationExecutor<User> {
	User findByCode(String code);

	User findByMobile(String mobile);
}
