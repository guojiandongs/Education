package close.gxph.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.bunny.repository.CoreResHessianDao;
import close.gxph.core.common.modules.persistence.DynamicSpecifications;
import close.gxph.core.common.modules.persistence.SearchFilter;
import close.gxph.core.entity.CoreRes;
import close.gxph.core.repository.CoreResDao;
import close.gxph.core.repository.CoreRoleResDao;

/***
 * 
 * @author g
 *
 */
@Transactional
@Service
public class CoreResService implements CoreResHessianDao{
	@Autowired
	private CoreResDao resourceDao;
	@Autowired
	private CoreRoleResDao coreRoleResDao;
	/**新增资源*/
	public CoreRes saveResource(CoreRes resource){
		return resourceDao.saveAndFlush(resource);
	}
	/**删除资源*/
	public void deleteResource(String id){
		coreRoleResDao.deleteByResourceid(id);
		resourceDao.delete(id);
	}
	/**查询*/
	public CoreRes findOne(String id){
		return resourceDao.findOne(id);
	}
	/**查询所有资源*/
	public List<CoreRes> findAll(Map<String,Object> searchParams){
		Specification<CoreRes> spec = buildSpecification(searchParams);
		List<CoreRes> list=resourceDao.findAll(spec);
		return list;
	}
	private Specification<CoreRes> buildSpecification(
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<CoreRes> spec = DynamicSpecifications.bySearchFilter(filters.values(), CoreRes.class);
		return spec;
	}
	
	/** 用户登录成功之后 加载 菜单 */
	public List<CoreRes> findParentRes(String parentid){
		List<CoreRes> crrList= resourceDao.findAllByParentid(parentid);
		return crrList;
	}
}
