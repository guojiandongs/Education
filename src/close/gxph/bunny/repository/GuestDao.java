package close.gxph.bunny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.Guest;

public interface GuestDao extends JpaRepository<Guest, String>,
		JpaSpecificationExecutor<Guest> {
	List<Guest> findAllByUsername(String name);
	List<Guest> findAllByMobile(String mobile);
	List<Guest> findAllByOpenid(String openid);
}
