package close.gxph.core.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import close.gxph.bunny.entity.AdPic;
import close.gxph.core.entity.CoreRes;


public interface CoreResHessianDao{
	/**新增资源*/
	CoreRes saveResource(CoreRes resource);
	/**删除资源*/
	void deleteResource(String id);
	/**查询*/
	CoreRes findOne(String id);
	/**查询所有资源*/
	List<CoreRes> findAll(Map<String,Object> searchParams);
	/** 用户登录成功之后 加载 菜单 */
	List<CoreRes> findParentRes(String parentid);
}
