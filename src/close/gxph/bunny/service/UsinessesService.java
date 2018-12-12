package close.gxph.bunny.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.repository.AdPicDao;
import close.gxph.bunny.repository.ApFileEnclosureDao;
import close.gxph.bunny.repository.UsinessesDao;
import close.gxph.core.common.util.CommonService;
import close.gxph.core.constant.Contants;
import close.gxph.core.entity.CoreRole;
import close.gxph.core.entity.CoreUserRole;
import close.gxph.core.repository.CoreRoleDao;
import close.gxph.core.repository.CoreUserRoleDao;
@Component
@Transactional
public class UsinessesService extends CommonService<Usinesses>{
	@Autowired
	private UsinessesDao usinessesDao;
	@Autowired
	private CoreUserRoleDao coreUserRoleDao;
	@Autowired
	private CoreRoleDao coreRoleDao;
	
	
	public void addUsinesses(Usinesses usinesses) {
		usinesses.setRegtime(new Timestamp(System.currentTimeMillis()));
		usinesses.setStatus("0");
		Usinesses us = usinessesDao.save(usinesses);
		//商户添加角色
		CoreRole role = coreRoleDao.findByCode("2");
		coreUserRoleDao.deleteByUserid(us.getId());
		CoreUserRole cur=new CoreUserRole();
		cur.setUserid(us.getId());
		cur.setRoleid(role.getId());
		coreUserRoleDao.saveAndFlush(cur);
	}

	/**
	 * 编辑信息.
	 */
	public void alterUsinesses(Usinesses usinesses) {
		String id = usinesses.getId();
		Usinesses us = usinessesDao.getOne(id);
		if(null!=us){
			usinesses.setRegtime(us.getRegtime());
		}
		usinessesDao.save(usinesses);
	}
	
	/**
	 * 获取信息.
	 */
	public Usinesses getUsinesses(String id) {
		return usinessesDao.findOne(id);
	}

	public List<Usinesses> listAll() {
		return usinessesDao.findAll();
	}
	
	/**
	 * 根据条件返回信息列表.
	 */
	public Page<Usinesses> getAdPics(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy,orderType);
		Specification<Usinesses> spec = buildSpecification(searchParams);
		return usinessesDao.findAll(spec, pageRequest);
	}

	public void deleteAdPic(String id) {
		usinessesDao.delete(id);
	}
	
	/**
     * 检查登陆名是否存在
     * @param name
     * @return
     */
    public Usinesses checkname(String username){
    	return usinessesDao.findByUsername(username);
    }
}
