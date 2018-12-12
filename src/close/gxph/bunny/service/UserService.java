package close.gxph.bunny.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.bunny.entity.User;
import close.gxph.bunny.repository.UserDao;
import close.gxph.core.common.modules.persistence.DynamicSpecifications;
import close.gxph.core.common.modules.persistence.SearchFilter;
import close.gxph.core.common.util.Clock;
import close.gxph.core.common.util.NumberX;
import close.gxph.core.common.util.StringX;
//import close.gxph.core.entity.Auth;

@Component
@Transactional
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
//	@Config("user.pwd")
//	private String userPwd;
//	@Config("user.pwd.sms")
//	private String userPwdSMS;
//	@Config("mail.enable")
//	private boolean mailable;
//	@Config("mail.domain")
//	private String mailDomain;

	@Autowired
	private UserDao userDao;
	
	private Clock clock = Clock.DEFAULT;

	/**
	 * 获取用户集合.
	 */
	public Page<User> getAllUsers(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy) {
		PageRequest pageRequest = buildPageRequest(pageNumber,pageSize,sortBy);
		Specification<User> spec = buildSpecification(searchParams);
		return userDao.findAll(spec,pageRequest);
	}

	/**
	 * 创建用户.
	 */
	public void addUser(User user) {
		user.setCreatedate(clock.getCurrentTime());
		String pwd = user.getPwd();
		user.setPwd(StringX.md5(pwd.getBytes()).toLowerCase());
		userDao.save(user);
	}

	/**
	 * 编辑用户.
	 */
	public void alterUser(User user) {
		userDao.save(user);
	}

	/**
	 * 删除用户.
	 */
	public void deleteUser(String id) {
/*		if (StringX.equals(code, "admin")) {
			logger.warn("操作员{}尝试删除超级管理员用户", (String) SecurityUtils.getSubject()
					.getPrincipal());
			throw new ServiceException("不能删除超级管理员用户");
		} else {
			userDao.delete(code);
		}*/
		userDao.delete(id);
	}

	/**
	 * 根据用户名返回用户.
	 */
	public User getUser(String code) {
		return userDao.findByCode(code);
	}
    /**
     * 根据ID返回用户
     */
	public User getOne(String id){
		return userDao.findOne(id);
	}
	/**
	 * 根据电话返回用户.
	 */
	public User getUserByMobile(String mobile) {
		return userDao.findByMobile(mobile);
	}

	/**
	 * 重置用户密码
	 */
	public void resetPassword(User user) {
		String newPassword = NumberX.getRandom(10000000, 99999999) + "";
		String newPasswordMd5 = StringX.md5(newPassword.getBytes());
		user.setPwd(newPasswordMd5.toLowerCase());
		alterUser(user);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<User> buildSpecification(
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<User> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), User.class);
		return spec;
	}
    private PageRequest buildPageRequest(int pageNumber,int pageSize,
    		String sortType){
    	Sort sort = null ;
    	if("auto".equals(sortType)){
    		sort = new Sort(Direction.DESC,"id");
    	} else if("asc".equals(sortType)){
    		sort = new Sort(Direction.ASC,"id");
    	}
    	return new PageRequest(pageNumber - 1,pageSize,sort);
    }

    public List<User> listAll(){
    	return userDao.findAll();
    }
    //查询所有用户的名字
    public List<String> findAllName(){
    	List<User> users = this.listAll();
    	List<String> usernames = new ArrayList<String>();
    	for(int i=0;i<users.size();i++){
    		String username = users.get(i).getName();
    		usernames.add(username);
    	}
    	return usernames ;
    }
    
    /**
     * 检查用户名是否存在
     * @param name
     * @return
     */
    public User checkname(String name){
    	return userDao.findByCode(name);
    }
}
