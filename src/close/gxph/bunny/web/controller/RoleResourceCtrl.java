package close.gxph.bunny.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.repository.CoreRoleResHessianDao;
import close.gxph.core.repository.CoreUserRoleHessianDao;
import close.gxph.core.service.CoreRoleResService;

/***
 * 为角色分配资源
 * @author g
 *
 */
@Controller
@RequestMapping(value="/coreRoleResource")
public class RoleResourceCtrl {
	/*@Autowired
	private CoreRoleResService coreRoleResService;*/
	CoreRoleResHessianDao coreRoleResService = (CoreRoleResHessianDao) new HessianUtil().getHessianProxyFactory(CoreRoleResHessianDao.class, "coreRoleResService");
	
	/**查询角色 资源*/
	@RequestMapping(value="/role/{roleid}")
	public String list(@PathVariable("roleid") String roleid,ModelMap model){
		System.out.println("角色ID："+roleid);
		model.addAttribute("list", coreRoleResService.list(roleid));
		model.addAttribute("roleid", roleid);
		return toList;
	}
	
	/**保存角色的资源 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(String roleid,String resourceids){
		coreRoleResService.save(roleid, resourceids);
		return toBack;
	}
	
	private String toList="sys/core/roleresource/list";
	private String toBack="redirect:/coreRole/list";
}
