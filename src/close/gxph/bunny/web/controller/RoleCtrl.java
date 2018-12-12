package close.gxph.bunny.web.controller;

import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.entity.CoreRole;
import close.gxph.core.repository.CoreRoleHessianDao;
import close.gxph.core.repository.CoreUserRoleHessianDao;
import close.gxph.core.service.CoreRoleService;

/**
 * 
 * @author g
 * 角色管理
 *
 */
@Controller
@RequestMapping(value="/coreRole")
public class RoleCtrl {
	private Logger logger=Logger.getLogger(this.getClass());
	CoreRoleHessianDao roleService = (CoreRoleHessianDao) new HessianUtil().getHessianProxyFactory(CoreRoleHessianDao.class, "coreRoleService");
	/**新增角色*/
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(ModelMap model){
		logger.info("新增角色");
		model.addAttribute("action", "create");
		model.addAttribute("role", new CoreRole());
		return toForm;
	}
	/**更新角色*/
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(ModelMap model,@PathVariable("id") String id){
		logger.info("更新角色："+id);
		model.addAttribute("action", "update");
		model.addAttribute("role", roleService.findOne(id));
		return toForm;
	}
	/**保存角色*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(CoreRole role){
		logger.info("保存角色");
		roleService.saveRole(role);
		return toRedirect;
	}
	/**删除角色*/
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id,
			Model model, ServletRequest request){
		logger.info("删除角色");
		CoreRole role = roleService.findOne(id);
		if(!role.getCode().equals("")){
			return toRedirect;
		}
		roleService.delete(id);
		return toRedirect;
	}
	/**查询所有角色*/
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = "15") int pageSize,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "sortby", required = false) String sortBy,
			Model model, ServletRequest request){
		logger.info("查询所有角色");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<CoreRole> lists =  roleService.getCoreRole(searchParams, pageNumber, pageSize, sortBy, order);
		model.addAttribute("list", lists);
		return toList;
	}
	private String toForm="sys/core/role/form";//编辑角色
	private String toList="sys/core/role/list";//角色列表
	private String toRedirect="redirect:/coreRole/list";//重定向列表
	
	/*@Autowired
	private CoreRoleService roleService;*/
}
