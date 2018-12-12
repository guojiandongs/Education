package close.gxph.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import close.gxph.bunny.entity.User;
import close.gxph.core.entity.CoreRole;
import close.gxph.core.entity.CoreRoleRes;
import close.gxph.core.entity.CoreUserRole;

public interface CoreUserRoleHessianDao{
	List<CoreRoleRes> loadResource(String userid);
	List<User> userList();
	List<CoreRole> queryCoreRoles();
	CoreUserRole getCoreUserRoleByUserid(String userid);
	void save(String userid,String roleid);
}
