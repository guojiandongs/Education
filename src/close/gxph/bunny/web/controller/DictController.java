package close.gxph.bunny.web.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import close.gxph.bunny.entity.Dict;
import close.gxph.bunny.repository.AreaHessianDao;
import close.gxph.bunny.repository.DictHessianDao;
import close.gxph.bunny.service.DictService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.constant.Contants;

import com.google.common.collect.Maps;
@Controller
@RequestMapping(value = "/dict")
public class DictController {
	/*@Autowired
	private DictService dictService ;*/
	private static DictHessianDao dictService;
	private static Map<String,String> sortTypes = Maps.newLinkedHashMap();
	static{
		sortTypes.put("auto", "自动(降序)");
		sortTypes.put("asc", "升序");
		dictService = (DictHessianDao) new HessianUtil().getHessianProxyFactory(DictHessianDao.class, "dictService");
	}
	//打开列表
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value="page",defaultValue="1") int pageNumber,
			           @RequestParam(value="page.size",defaultValue=Contants.PAGE_SIZE) int pageSize,
			           @RequestParam(value="sortType",defaultValue="auto") String sortType,
			           Model model,ServletRequest request){
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<Dict> lists = dictService.getDicts(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("dicts", lists);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "gxph/dict/list";
	}
	//新增字典信息
	@RequestMapping(method = RequestMethod.POST ,value = "create")
	public String create(Dict newDict,RedirectAttributes redirectAttributes){
		dictService.addDict(newDict);
		redirectAttributes.addFlashAttribute("message", "新增字典成功");
		return "redirect:/dict" ;
	}
	//删除字典信息
	@RequestMapping(method = RequestMethod.GET, value="delete/{id}")
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes){
		dictService.deleteDict(id);
		redirectAttributes.addFlashAttribute("message", "删除字典成功");
		return "redirect:/dict" ;
	}
	
	@RequestMapping(method = RequestMethod.GET,value="create")
	public String create(ModelMap model){
		model.addAttribute("action", "create");
		return "gxph/dict/form" ;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(@PathVariable("id") String id, ModelMap model){
		model.addAttribute("action", "update");
		model.addAttribute("dict", dictService.getDict(id));
		return "gxph/dict/form" ;
	}
	
	@RequestMapping(method = RequestMethod.POST,value="update")
	public String update(Dict dict,RedirectAttributes redirectAttributes){
		dictService.alterDict(dict);
		redirectAttributes.addFlashAttribute("message","字典编辑成功");
		return "redirect:/dict";
	}

}
