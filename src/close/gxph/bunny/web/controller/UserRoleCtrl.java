package close.gxph.bunny.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.repository.CoreUserRoleHessianDao;
import close.gxph.core.service.CoreUserRoleService;

/***
 * 
 * 用户角色分配
 * @author g
 */
@Controller
@RequestMapping(value="/coreUserRole")
public class UserRoleCtrl {
	/*@Autowired
	private CoreUserRoleService coreUserRoleService;*/
	CoreUserRoleHessianDao coreUserRoleService = (CoreUserRoleHessianDao) new HessianUtil().getHessianProxyFactory(CoreUserRoleHessianDao.class, "coreUserRoleService");
	/**查询用户及其角色信息*/
	@RequestMapping(value="/userList",method=RequestMethod.GET)
	public String userList(ModelMap model){
		model.addAttribute("list", coreUserRoleService.userList());
		return userList;
	}
	/**查询角色列表,定位到用户的角色*/
	@RequestMapping(value="/roleList/{userid}",method=RequestMethod.GET)
	public String queryRoles(@PathVariable("userid") String userid,ModelMap model){
		model.addAttribute("list", coreUserRoleService.queryCoreRoles());
		model.addAttribute("userrole",coreUserRoleService.getCoreUserRoleByUserid(userid));
		model.addAttribute("userid", userid);
		return roleList;
	}
	/**保存用户角色*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveRole(String userid,String roleid){
		System.out.println("用户的ID："+userid);
		System.out.println("角色的ID："+roleid);
		coreUserRoleService.save(userid, roleid);
		return redirectUser;
	}
	
	
	private String userList="sys/core/userrole/userlist";
	private String roleList="sys/core/userrole/rolelist";
	private String redirectUser="redirect:/coreUserRole/userList";
}
