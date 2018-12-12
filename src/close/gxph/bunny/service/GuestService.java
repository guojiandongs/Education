package close.gxph.bunny.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.bunny.entity.Guest;
import close.gxph.bunny.repository.GuestDao;
import close.gxph.core.common.modules.persistence.DynamicSpecifications;
import close.gxph.core.common.modules.persistence.SearchFilter;
@Component
@Transactional
public class GuestService{

	@Autowired
	private GuestDao guestDao;

	public Guest addGuest(Guest guest) {
		try {
			guest.setRegtime(new Timestamp(System.currentTimeMillis()));
			return guestDao.save(guest);
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}

	/**
	 * 编辑信息.
	 */
	public Guest alterGuest(Guest guest) {
		return guestDao.save(guest);
	}
	public Guest updateGuest(Guest guest) throws UnsupportedEncodingException{
		if(StringUtils.isEmpty(guest.getId())){
			throw new RuntimeException("没有id更新失败");
		}
		Guest guest2=guestDao.findOne(guest.getId());
		
		if(!StringUtils.isEmpty(guest.getName())){
			guest2.setName(java.net.URLDecoder.decode(guest.getName(),"UTF-8"));
		}
		if(!StringUtils.isEmpty(guest.getMobile())){
			guest2.setMobile(guest.getMobile());
		}
		if(!StringUtils.isEmpty(guest.getLicenceno())){
			guest2.setLicenceno(guest.getLicenceno());
		}
		if(!StringUtils.isEmpty(guest.getEmail())){
			guest2.setEmail(guest.getEmail());
		}
		if(!StringUtils.isEmpty(guest.getAddress())){
			guest2.setAddress(java.net.URLDecoder.decode(guest.getAddress(),"UTF-8"));
		}
		if(!StringUtils.isEmpty(guest.getState())){
			guest2.setState(guest.getState());
		}
		if(!StringUtils.isEmpty(guest.getBlack())){
			guest2.setBlack(guest.getBlack());
		}
		if(!StringUtils.isEmpty(guest.getUsername())){
			guest2.setUsername(guest.getUsername());
		}
		if(!StringUtils.isEmpty(guest.getUspassword())){
			guest2.setUspassword(guest.getUspassword());
		}
		if(!StringUtils.isEmpty(guest.getHeaderimg())){
			guest2.setHeaderimg(guest.getHeaderimg());
		}
		return guestDao.saveAndFlush(guest2);
	}
	
	/**
	 * 获取信息.
	 */
	public Guest getGuest(String id) {
		return guestDao.findOne(id);
	}
	
	public List<Guest> findAllByUsername(String name) {
		return guestDao.findAllByUsername(name);
	}
	public List<Guest> findAllByPhone(String phone) {
		return guestDao.findAllByMobile(phone);
	}
	public List<Guest> findAll(){
		return guestDao.findAll();
	}
	/**
	 * 根据条件返回信息列表.
	 */
	public Page<Guest> getGuests(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy);
		Specification<Guest> spec = buildSpecification(searchParams);
		return guestDao.findAll(spec, pageRequest);
	}

	public void deleteGuest(String id) {
		guestDao.delete(id);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("asc".equals(sortType)) {
			sort = new Sort(Direction.ASC, "id");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Guest> buildSpecification(
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Guest> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), Guest.class);
		return spec;
	}
	/**
	 * 根据openid获取用户信息
	 * @param openid
	 * @return
	 */
	public List<Guest> findAllByOpenid(String openid) {
		return guestDao.findAllByOpenid(openid);
	}
}
