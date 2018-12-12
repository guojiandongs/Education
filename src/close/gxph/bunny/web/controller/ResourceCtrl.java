package close.gxph.bunny.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import close.gxph.bunny.repository.AdPicHessianDao;
import close.gxph.bunny.repository.CoreResHessianDao;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.entity.CoreRes;
import close.gxph.core.service.CoreResService;

/***
 * 菜单管理
 * @author g
 * 
 */
@Controller
@RequestMapping(value="/coreResource")
public class ResourceCtrl {

	private Logger logger=Logger.getLogger(this.getClass());
	/*@Autowired
	private CoreResService resourceService;*/
	CoreResHessianDao resourceService = (CoreResHessianDao) new HessianUtil().getHessianProxyFactory(CoreResHessianDao.class, "coreResService");
	/**跳转到新增页面*/
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(ModelMap model){
		logger.info("跳转到新增页面");
		model.addAttribute("action", "create");
		List<CoreRes> parentres = resourceService.findParentRes("0");
		model.addAttribute("parentres", parentres);
		model.addAttribute("resource", new CoreRes());
		return toForm;
	}
	/**跳转到修改页面*/
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(ModelMap model,@PathVariable("id") String id){
		logger.info("跳转到新增页面："+id);
		List<CoreRes> parentres = resourceService.findParentRes("0");
		model.addAttribute("action", "update");
		model.addAttribute("parentres", parentres);
		model.addAttribute("resource", resourceService.findOne(id));
		return toForm;
	}
	/**删除菜单*/
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id){
		logger.info("删除菜单："+id);
		resourceService.deleteResource(id);
		return toRedirect;
	}
	/**保存菜单*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(CoreRes resource){
		logger.info("保存菜单");
		try {
			if(resource.getLeaf().equals("0")){
				resource.setParentid("0");
			}
			resourceService.saveResource(resource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toRedirect;
	}
	/**查询所有菜单*/
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(ModelMap model,ServletRequest request){
		logger.info("查询所有菜单");
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		model.addAttribute("list", resourceService.findAll(searchParams));
		return toList;
	}
	
	
	
	
	private String toForm="sys/core/resource/form";//跳转到表单页面
	private String toList="sys/core/resource/list";//跳转到列表页面
	private String toRedirect="redirect:/coreResource/list";//重定向到列表页面
}
