package close.gxph.bunny.web.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.TreeMap;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.bunny.entity.Dict;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.repository.AdPicHessianDao;
import close.gxph.bunny.repository.UsinessesHessianDao;
import close.gxph.bunny.service.ApFileEnclosureService;
import close.gxph.bunny.service.DictService;
import close.gxph.bunny.service.AdPicService;
import close.gxph.bunny.service.UsinessesService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.constant.Contants;
/**
 * 商户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/usinesses")
public class UsinessesController {
	/*@Autowired
	private UsinessesService usinessesService;*/
	private static UsinessesHessianDao usinessesService;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动(降序)");
		sortTypes.put("asc", "升序");
		usinessesService = (UsinessesHessianDao) new HessianUtil().getHessianProxyFactory(UsinessesHessianDao.class, "usinessesService");
	}
	
	// 打开列表
	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Contants.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "asc") String sortType,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<Usinesses> lists = usinessesService.getAdPics(searchParams, pageNumber,
				pageSize, "regtime",sortType);
		/*for (AdPic adPic : lists) {
			Map<String,Object> searchParam = new TreeMap<String,Object>();
	        searchParam.put("EQ_objId", adPic.getId());
	        searchParam.put("EQ_deleteState", Contants.DEL_STATE_NO);
	        List<ApFileEnclosure>  apFileEnclosures = apFileEnclosureService.findToaFileEnclosureList(searchParam);
	        adPic.setApFileEnclosures(apFileEnclosures);
		}*/
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("usinesses", lists);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "gxph/usinesses/list";
	}
	
	// 打开列表
		@RequestMapping(method = RequestMethod.GET,value="validate")
		public String validate(
				@RequestParam(value = "page", defaultValue = "1") int pageNumber,
				@RequestParam(value = "page.size", defaultValue = Contants.PAGE_SIZE) int pageSize,
				@RequestParam(value = "sortType", defaultValue = "asc") String sortType,
				Model model, ServletRequest request) {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(
					request, "search_");
			Page<Usinesses> lists = usinessesService.getAdPics(searchParams, pageNumber,
					pageSize, "regtime",sortType);
			/*for (AdPic adPic : lists) {
				Map<String,Object> searchParam = new TreeMap<String,Object>();
		        searchParam.put("EQ_objId", adPic.getId());
		        searchParam.put("EQ_deleteState", Contants.DEL_STATE_NO);
		        List<ApFileEnclosure>  apFileEnclosures = apFileEnclosureService.findToaFileEnclosureList(searchParam);
		        adPic.setApFileEnclosures(apFileEnclosures);
			}*/
			model.addAttribute("sortTypes", sortTypes);
			model.addAttribute("sortType", sortType);
			model.addAttribute("usinesses", lists);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets
					.encodeParameterStringWithPrefix(searchParams, "search_"));
			return "gxph/usinesses/validatelist";
		}

	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		usinessesService.deleteAdPic(id);
		redirectAttributes.addFlashAttribute("message", "删除广告成功");
		return "redirect:/usinesses";
	}

	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(ModelMap model) {
		model.addAttribute("action", "create");
		/*List<Dict> dicts=dictService.getDicts("ad_type");
		model.addAttribute("dicts", dicts);*/
		return "gxph/usinesses/form";
	}

	@RequestMapping(method = RequestMethod.POST, value = "create")
	public String create(HttpServletRequest request,HttpServletResponse response,Usinesses usinesses, RedirectAttributes redirectAttributes) {
		try {
			usinessesService.addUsinesses(usinesses);
			redirectAttributes.addFlashAttribute("message", "商户添加成功");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "商户添加失败");
			e.printStackTrace();
		}
		return "redirect:/usinesses";
	}

	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("action", "update");
		Usinesses usinesses=usinessesService.getUsinesses(id);
		model.addAttribute("usinesses",usinesses);
		return "gxph/usinesses/form";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "updatevalidate/{id}")
	public String updatevalidate(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("action", "update");
		Usinesses usinesses=usinessesService.getUsinesses(id);
		model.addAttribute("usinesses",usinesses);
		return "gxph/usinesses/validate";
	}

	@RequestMapping(method = RequestMethod.POST, value = "update")
	public String update(HttpServletRequest request,HttpServletResponse response,Usinesses usinesses, RedirectAttributes redirectAttributes) {
		try {
			usinessesService.alterUsinesses(usinesses);
			redirectAttributes.addFlashAttribute("message", "商户编辑成功");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "商户编辑失败");
			e.printStackTrace();
		}
		return "redirect:/usinesses";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "validateform")
	public String validateform(HttpServletRequest request,HttpServletResponse response,Usinesses usinesses, RedirectAttributes redirectAttributes) {
		try {
			Usinesses us = usinessesService.getUsinesses(usinesses.getId());
			us.setStatus(usinesses.getStatus());
			usinessesService.alterUsinesses(us);
			redirectAttributes.addFlashAttribute("message", "商户审核成功");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "商户审核失败");
			e.printStackTrace();
		}
		return "redirect:/usinesses/validate";
	}
	
}
