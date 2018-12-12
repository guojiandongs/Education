package close.gxph.bunny.web.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.bunny.entity.Information;
import close.gxph.bunny.entity.InformationType;
import close.gxph.bunny.entity.LoginUser;
import close.gxph.bunny.entity.SubjectClass;
import close.gxph.bunny.repository.InformationHessianDao;
import close.gxph.bunny.repository.InformationTypeHessianDao;
import close.gxph.bunny.repository.SubjectClassHessianDao;
import close.gxph.bunny.service.ApFileEnclosureService;
import close.gxph.bunny.service.InformationService;
import close.gxph.bunny.service.InformationTypeService;
import close.gxph.bunny.service.SubjectService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.bunny.util.UserType;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.constant.Contants;

/**
 * 资讯管理
 * @author g
 *
 */
@Controller
@RequestMapping(value = "/infor")
public class InformationController {
	/*@Autowired
	private static InformationService informationService;*/
	private static InformationHessianDao informationService;
	/*@Autowired
	private InformationTypeService informationTypeService;*/
	private static InformationTypeHessianDao informationTypeService;
	/*@Autowired
	private SubjectService subjectService;*/
	private static SubjectClassHessianDao subjectService;
	@Autowired
	public ApFileEnclosureService apFileEnclosureService;
	
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "降序");
		sortTypes.put("asc", "自动(升序)");
		informationService = (InformationHessianDao) new HessianUtil().getHessianProxyFactory(InformationHessianDao.class, "informationService");
		informationTypeService = (InformationTypeHessianDao) new HessianUtil().getHessianProxyFactory(InformationTypeHessianDao.class, "informationTypeService");
		subjectService = (SubjectClassHessianDao) new HessianUtil().getHessianProxyFactory(SubjectClassHessianDao.class, "subjectService");
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
		Page<Information> lists = informationService.getInformationPics(searchParams, pageNumber,
				pageSize,"recordtime",sortType);
		List<InformationType> typelist = informationTypeService.listAllByStatusAndIsbelongtype(Contants.BELONG_STATE_YES);
		List<SubjectClass> classlist = subjectService.getAllSubjectClassByStatus();
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("informationTypelist", typelist);
		model.addAttribute("subjectClasslist", classlist);
		model.addAttribute("infor", lists);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "gxph/information/list";
	}

	//删除
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		informationService.deleteInformation(id);
		redirectAttributes.addFlashAttribute("message", "删除资讯成功");
		return "redirect:/infor";
	}

	//跳转到添加页面
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(ModelMap model) {
		model.addAttribute("action", "create");
		List<InformationType> typelist = informationTypeService.listAllByStatusAndIsbelongtype(Contants.BELONG_STATE_YES);
		List<SubjectClass> classlist = subjectService.getAllSubjectClassByStatus();
		model.addAttribute("informationTypelist", typelist);
		model.addAttribute("subjectClasslist", classlist);
		return "gxph/information/form";
	}

	/**
	 * 添加资讯
	 * @param request
	 * @param response
	 * @param information
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "create")
	public String create(HttpServletRequest request,HttpServletResponse response,Information information, RedirectAttributes redirectAttributes) {
		try {
			LoginUser lu = new UserType().getUserType();
			information.setRecorduserid(lu.getRecorduserid());
			information.setRecordusername(lu.getRecordusername());
			information.setRecordtime(lu.getRecordtime());
			String ifid = informationService.alterInformation(information);
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,ifid,Contants.OBJ_TYPE_FQGL,new ArrayList<String>());
			if(b){
				redirectAttributes.addFlashAttribute("message", "资讯添加成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "资讯添加失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "资讯添加失败");
			e.printStackTrace();
		}
		return "redirect:/infor";
	}
	
	/**
	 * 编辑资讯
	 * @param request
	 * @param response
	 * @param information
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public String update(HttpServletRequest request,HttpServletResponse response,Information information, RedirectAttributes redirectAttributes) {
		try {
			String ifid = informationService.alterInformation(information);
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,ifid,Contants.OBJ_TYPE_ZX,new ArrayList<String>());
			if(b){
				redirectAttributes.addFlashAttribute("message", "资讯编辑成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "资讯编辑失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "资讯编辑失败");
			e.printStackTrace();
		}
		return "redirect:/infor";
	}

	//跳转到修改页面
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(@PathVariable("id") String id, ModelMap model) {
		try {
			model.addAttribute("action", "update");
			Information im=informationService.getInformation(id);
			List<ApFileEnclosure> list = apFileEnclosureService.findByObjId(id);
			if(null!=list&&list.size()>0){
				im.setApFileEnclosure(list.get(0));
			}
			model.addAttribute("info",im);
			List<InformationType> typelist = informationTypeService.listAllByStatusAndIsbelongtype(Contants.BELONG_STATE_YES);
			List<SubjectClass> classlist = subjectService.getAllSubjectClassByStatus();
			model.addAttribute("informationTypelist", typelist);
			model.addAttribute("subjectClasslist", classlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gxph/information/form";
	}
}
