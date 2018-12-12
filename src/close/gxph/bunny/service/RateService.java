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
import close.gxph.bunny.entity.Rate;
import close.gxph.bunny.repository.AdPicDao;
import close.gxph.bunny.repository.ApFileEnclosureDao;
import close.gxph.bunny.repository.RateDao;
import close.gxph.bunny.util.UserType;
import close.gxph.core.common.util.CommonService;
@Component
@Transactional
public class RateService extends CommonService<Rate>{

	@Autowired
	private RateDao rateDao;

	public String addRate(Rate rate) {
		Rate ra = rateDao.save(rate);
		return ra.getId();
	}

	/**
	 * 编辑信息.
	 */
	public String alterRate(Rate rate) {
		Rate ra = rateDao.save(rate);
		return ra.getId();
	}
	
	/**
	 * 获取信息.
	 */
	public Rate getRate(String id) {
		return rateDao.findOne(id);
	}
	
	public List<Rate> listAll() {
		return rateDao.findAll();
	}
	
	/**
	 * 根据条件返回信息列表.
	 */
	public Page<Rate> getAdPics(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy,orderType);
		Specification<Rate> spec = buildSpecification(searchParams);
		return rateDao.findAll(spec, pageRequest);
	}

	public void deleteAdPic(String id) {
		rateDao.delete(id);
	}

}
