package close.gxph.bunny.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import close.gxph.bunny.entity.SubjectClass;
import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.repository.SubjectClassDao;
import close.gxph.core.common.modules.persistence.DynamicSpecifications;
import close.gxph.core.common.modules.persistence.SearchFilter;
import close.gxph.core.constant.Contants;

/***
 * 
 * @author g
 * 主题分类
 *
 */
@Component
@Transactional
public class SubjectService {
	@Autowired
	private SubjectClassDao subjectClassDao;
	/*@Autowired
	private SubjectClassScenicDao subjectClassScenicDao;*/
	
	//新增 主题分类
	public String add(SubjectClass subjectClass){
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
		subjectClass.setRecordtime(new Timestamp(System.currentTimeMillis()));
		subjectClass.setRecorduserid(recorduserid);
		subjectClass.setRecordusername(recordusername);
		return subjectClassDao.saveAndFlush(subjectClass).getId();
	}
	//删除主题分类
	public void delete(SubjectClass subjectClass){
		subjectClassDao.delete(subjectClass);
	}
	//删除主题分类
	public void deleteSub(String subId){
		subjectClassDao.delete(subId);
	}
	//更新主题分类
	public void update(SubjectClass obj){
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
		obj.setRecordtime(new Timestamp(System.currentTimeMillis()));
		obj.setRecorduserid(recorduserid);
		obj.setRecordusername(recordusername);
		subjectClassDao.saveAndFlush(obj);
	}
	//查询单个主题分类
	public SubjectClass getSub(String id){
		return subjectClassDao.findOne(id);
	}
	//分页查询主题分类
	public Page<SubjectClass> getSubjectClass(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String sorttype) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy,sorttype);
		Specification<SubjectClass> spec = buildSub(searchParams);
		return subjectClassDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 查询主题列表
	 * @return
	 */
	public List<SubjectClass> getAllSubjectClass(){
		return subjectClassDao.findAll();
	}
	
	/**
	 * 查询主题列表
	 * @return
	 */
	public List<SubjectClass> getAllSubjectClassByStatus(){
		return subjectClassDao.findByState("1");
	}
	
	private PageRequest buildPageRequest(int pageNumber, int pagzSize,String sortby,
			String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, sortby);
		} else if ("asc".equals(sortType)) {
			sort = new Sort(Direction.ASC, sortby);
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
	
	private Specification<SubjectClass> buildSub(
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<SubjectClass> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), SubjectClass.class);
		return spec;
	}
}
