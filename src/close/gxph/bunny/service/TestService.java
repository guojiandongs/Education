package close.gxph.bunny.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.bunny.entity.LoginUser;
import close.gxph.bunny.repository.AdPicDao;
import close.gxph.bunny.repository.ApFileEnclosureDao;
import close.gxph.bunny.util.UserType;
import close.gxph.core.common.util.CommonService;
@Component
@Transactional
@Controller
@RequestMapping(value="/test/order")
public class TestService extends CommonService<AdPic>{

	@Autowired
	private AdPicDao adPicDao;
	@Autowired
	private ApFileEnclosureDao apFileEnclosureDao;

	@RequestMapping(value="/all")
	public String addAdPic(AdPic adPic) {
		LoginUser lu = new UserType().getUserType();
		adPic.setRecorduserid(lu.getRecorduserid());
		adPic.setRecordusername(lu.getRecordusername());
		adPic.setRecordtime(lu.getRecordtime());
		AdPic ad = adPicDao.save(adPic);
		return ad.getId();
	}

	/**
	 * 编辑信息.
	 */
	public String alterAdPic(AdPic adPic) {
		LoginUser lu = new UserType().getUserType();
		adPic.setRecorduserid(lu.getRecorduserid());
		adPic.setRecordusername(lu.getRecordusername());
		adPic.setRecordtime(lu.getRecordtime());
		AdPic ad = adPicDao.save(adPic);
		return ad.getId();
	}
	
	/**
	 * 获取信息.
	 */
	public AdPic getAdPic(String id) {
		return adPicDao.findOne(id);
	}
	public AdPic getAdBySeq(Integer seq) {
		return adPicDao.findAdBySeq(seq);
	}
	
	public List<AdPic> listAll() {
		return adPicDao.findAll();
	}
	public List<AdPic> findAdBySeqAll() {
		return adPicDao.findAdBySeqAll();
	}
	
	/**
	 * 根据条件返回信息列表.
	 */
	public Page<AdPic> getAdPics(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy,orderType);
		Specification<AdPic> spec = buildSpecification(searchParams);
		return adPicDao.findAll(spec, pageRequest);
	}

	public void deleteAdPic(String id) {
		adPicDao.delete(id);
		List<ApFileEnclosure> list = apFileEnclosureDao.findByObjIdAndDeleteState(id,"0");
		if(list.size()>0){
			apFileEnclosureDao.delete(list);
		}
	}

}
