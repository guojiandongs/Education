package close.gxph.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import close.gxph.core.entity.CoreRoleRes;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoreRoleResDao extends JpaRepository<CoreRoleRes, String>,JpaSpecificationExecutor<CoreRoleRes>{
	
	public List<CoreRoleRes> findAllByRoleid(String roleid);
	
	/**
	 * 查询角色权限信息
	 */
	@Modifying
	@Query("select c from CoreRoleRes c left join c.coreRes d where c.roleid=? and d.parentid=? order by d.xh asc")
	public List<CoreRoleRes> findAllCoreRoleRes(String roleid,String parentid);
	
	/**删除 角色资源*/
	@Modifying
	@Query(value="delete from CoreRoleRes where roleid=:roleid")
	public void deleteByRoleid(@Param("roleid") String roleid);
	
	/**删除 资源*/
	@Modifying
	@Query(value="delete from CoreRoleRes where resourceid=:resourceid")
	public void deleteByResourceid(@Param("resourceid") String resourceid);
}
