package close.gxph.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.bunny.entity.User;
import close.gxph.bunny.repository.UserDao;
import close.gxph.core.constant.Contants;
import close.gxph.core.entity.CoreRes;
import close.gxph.core.entity.CoreRole;
import close.gxph.core.entity.CoreRoleRes;
import close.gxph.core.entity.CoreUserRole;
import close.gxph.core.repository.CoreResDao;
import close.gxph.core.repository.CoreRoleDao;
import close.gxph.core.repository.CoreRoleResDao;
import close.gxph.core.repository.CoreUserRoleDao;

/***
 * 
 * @author g
 * 用户角色
 */
@Service
@Transactional
public class CoreUserRoleService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private CoreUserRoleDao coreUserRoleDao;
	@Autowired
	private CoreRoleDao coreRoleDao;
	@Autowired
	private CoreRoleResDao coreRoleResDao;
	
	/**查询用户列表及其角色*/
	public List<User> userList(){
		List<User> userList=userDao.findAll();
		List<CoreUserRole> coreUserRoleList=coreUserRoleDao.findAll();
		for(User user:userList){
			for(CoreUserRole cur:coreUserRoleList){
				if(user.getId().equals(cur.getUserid())){
					user.setCoreRole(cur.getCoreRole());
				}
			}
		}
		return userList;
	}
	/**查询 角色列表*/
	public List<CoreRole> queryCoreRoles(){
		List<CoreRole> coreRoleList=coreRoleDao.findAll();
		return coreRoleList;
	}
	/**
	 * 根据用户id查找对应的角色
	 */
	public CoreUserRole getCoreUserRoleByUserid(String userid){
		CoreUserRole coreUserRolelist = coreUserRoleDao.findByUserid(userid);
		return coreUserRolelist;
		//return null;
	}
	/**
	 * 查询 根据用户id找到用户对应的角色（这个方法是错误的，findOne只能找到一条id的记录）
	 * @param userid
	 * @return
	 */
	public CoreUserRole getCoreUserRoles(String userid){
		CoreUserRole coreUserRole=coreUserRoleDao.findOne(userid);
		return coreUserRole;
	}
	/**保存 用户角色*/
	public void save(String userid,String roleid){
		coreUserRoleDao.deleteByUserid(userid);
		CoreUserRole cur=new CoreUserRole();
		cur.setUserid(userid);
		cur.setRoleid(roleid);
		coreUserRoleDao.saveAndFlush(cur);
	}
	
	/**用户登录成功之后 加载 菜单**/
	
	public List<CoreRoleRes> loadResource(String userid){
		CoreUserRole cur=coreUserRoleDao.findByUserid(userid);
		//List<CoreRoleRes> crrList= coreRoleResDao.findAllByRoleid(cur.getRoleid());
		List<CoreRoleRes> crrList = new ArrayList<CoreRoleRes>();
		if(cur!=null){
			crrList= coreRoleResDao.findAllCoreRoleRes(cur.getRoleid(), "0");
			CoreRole cr = coreRoleDao.getOne(cur.getRoleid());
			SecurityUtils.getSubject().getSession().setAttribute(Contants.ROLE_CODE, cr);
			cr.getCode();
			for (CoreRoleRes coreRoleRes : crrList) {
				CoreRes coreRes = coreRoleRes.getCoreRes();
				List<CoreRoleRes> childcrList= coreRoleResDao.findAllCoreRoleRes(cur.getRoleid(), coreRes.getId());
				for (CoreRoleRes coreRoleRes2 : childcrList) {
					coreRes.getChildcoreReslists().add(coreRoleRes2.getCoreRes());
				}
				coreRoleRes.setCoreRes(coreRes);
			}
			return crrList;
		}else{
			return crrList;
		}
		
	}
	
}
