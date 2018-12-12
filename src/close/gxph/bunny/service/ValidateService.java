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

import close.gxph.bunny.entity.Validate;
import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.repository.ValidateDao;
import close.gxph.bunny.repository.ApFileEnclosureDao;
import close.gxph.core.common.util.CommonService;
import close.gxph.core.constant.Contants;
@Component
@Transactional
public class ValidateService extends CommonService<Validate>{

	@Autowired
	private ValidateDao ValidateDao;
	

	public String addValidate(Validate Validate) {
		Session session = SecurityUtils.getSubject().getSession();
		User currenter = (User) session.getAttribute(Contants.CURRENT_USER);
		String recorduserid="";
		String recordusername = "";
		if(currenter!=null){
			recorduserid = currenter.getId();
			recordusername = currenter.getName();
		}else{
			Usinesses us = (Usinesses) session.getAttribute(Contants.CURRENT_US);
			recorduserid = us.getId();
			recordusername = us.getName();
		}
		Validate.setRecorduserid(recorduserid);
		Validate.setRecordusername(recordusername);
		Validate.setRecordtime(new Timestamp(System.currentTimeMillis()));
		Validate ad = ValidateDao.save(Validate);
		return ad.getId();
	}

	/**
	 * 编辑信息.
	 */
	public String alterValidate(Validate Validate) {
		Session session = SecurityUtils.getSubject().getSession();
		User currenter = (User) session.getAttribute(Contants.CURRENT_USER);
		String recorduserid="";
		String recordusername = "";
		if(currenter!=null){
			recorduserid = currenter.getId();
			recordusername = currenter.getName();
		}else{
			Usinesses us = (Usinesses) session.getAttribute(Contants.CURRENT_US);
			recorduserid = us.getId();
			recordusername = us.getName();
		}
		Validate.setRecorduserid(recorduserid);
		Validate.setRecordusername(recordusername);
		Validate.setRecordtime(new Timestamp(System.currentTimeMillis()));
		Validate ad = ValidateDao.save(Validate);
		return ad.getId();
	}
	
	public List<Validate> listAll() {
		return ValidateDao.findAll();
	}
	
	/**
	 * 根据条件返回信息列表.
	 */
	public Page<Validate> getValidates(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy,orderType);
		Specification<Validate> spec = buildSpecification(searchParams);
		return ValidateDao.findAll(spec, pageRequest);
	}

	public void deleteValidate(String id) {
		ValidateDao.delete(id);
	}
}
