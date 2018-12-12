package close.gxph.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import close.gxph.core.entity.CoreRole;
import close.gxph.core.repository.CoreRoleDao;
import close.gxph.core.repository.CoreRoleResDao;
import close.gxph.core.common.util.CommonService;
@Component
@Transactional
@Service
public class CoreRoleService extends CommonService<CoreRole>{

	@Autowired
	private CoreRoleDao roleDao;
	@Autowired
	private CoreRoleResDao coreRoleResDao;
	/**保存角色*/
	public CoreRole saveRole(CoreRole role){
		return roleDao.saveAndFlush(role);
	}
	/**查询所有角色*/
	public List<CoreRole> findAll(){
		return roleDao.findAll();
	}
	/**删除角色*/
	public void delete(String id){
		roleDao.delete(id);
		coreRoleResDao.deleteByRoleid(id);
	}
	/**查询*/
	public CoreRole findOne(String id){
		return roleDao.findOne(id);
	}
	/* 
	 * 根据条件进行角色查询
	 */
	public Page<CoreRole> getCoreRole(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy, String order) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortBy,order);
		Specification<CoreRole> spec = buildSpecification(searchParams);
		return roleDao.findAll(spec, pageRequest);
	}
}
