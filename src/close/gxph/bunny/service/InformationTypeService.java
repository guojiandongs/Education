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
import close.gxph.bunny.entity.InformationType;
import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.repository.AdPicDao;
import close.gxph.bunny.repository.ApFileEnclosureDao;
import close.gxph.bunny.repository.InformationTypeDao;
import close.gxph.core.common.util.CommonService;
import close.gxph.core.constant.Contants;
@Component
@Transactional
public class InformationTypeService extends CommonService<InformationType>{

	@Autowired
	private InformationTypeDao informationTypeDao;
	@Autowired
	private ApFileEnclosureDao apFileEnclosureDao;

	/**
	 * 编辑分区
	 * @param information
	 * @return
	 */
	public String alterInformation(InformationType information) {
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
		information.setRecorduserid(recorduserid);
		information.setRecordusername(recordusername);
		information.setRecordtime(new Timestamp(System.currentTimeMillis()));
		InformationType ifm = informationTypeDao.save(information);
		return ifm.getId();
	}

	/**
	 * 获取信息.
	 */
	public InformationType getInformation(String id) {
		return informationTypeDao.findOne(id);
	}
	
	/**
	 * 查询分区列表
	 * @return
	 */
	public List<InformationType> listAll() {
		return informationTypeDao.findAll();
	}
	
	/**
	 * 查询分区列表
	 * @return
	 */
	public List<InformationType> listAllByStatus() {
		return informationTypeDao.findByStatus("1");
	}
	
	/**
	 * 查询分区列表(资讯)
	 * @return
	 */
	public List<InformationType> listAllByStatusAndIsbelongtype(String status) {
		return informationTypeDao.findByStatusAndIsbelongtype(Contants.STATE_YES,status);
	}
	
	
	/**
	 * 根据条件返回信息列表.
	 */
	public Page<InformationType> getInformationPics(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy,orderType);
		Specification<InformationType> spec = buildSpecification(searchParams);
		return informationTypeDao.findAll(spec, pageRequest);
	}

	public void deleteInformation(String id) {
		informationTypeDao.delete(id);
		List<ApFileEnclosure> list = apFileEnclosureDao.findByObjIdAndDeleteState(id,"0");
		if(list.size()>0){
			apFileEnclosureDao.delete(list);
		}
	}
}
