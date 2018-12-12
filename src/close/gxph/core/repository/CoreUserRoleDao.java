package close.gxph.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import close.gxph.core.entity.CoreUserRole;

public interface CoreUserRoleDao extends JpaSpecificationExecutor<CoreUserRole>,JpaRepository<CoreUserRole, String>{
	@Modifying
	@Query(value="delete from CoreUserRole where userid=:userid")
	public void deleteByUserid(@Param("userid") String userid);
	
	public CoreUserRole findByUserid(String userid);
	
}
