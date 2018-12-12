package close.gxph.bunny.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.security.auth.Subject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import close.gxph.bunny.entity.LoginUser;
import close.gxph.bunny.entity.SubjectClass;
import close.gxph.bunny.repository.InformationTypeHessianDao;
import close.gxph.bunny.repository.SubjectClassHessianDao;
import close.gxph.bunny.service.ApFileEnclosureService;
import close.gxph.bunny.service.SubjectService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.bunny.util.UserType;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.constant.Contants;

import com.google.common.collect.Maps;

/***
 * 
 * 主题分类和主题分类包括的景区相关操作
 * @author g
 *
 */
@Controller
@RequestMapping(value = "/subject")
public class SubjectController {
	/*@Autowired
	private SubjectService subjectService;*/
	private static SubjectClassHessianDao subjectService;
	@Autowired
	public ApFileEnclosureService apFileEnclosureService;
	
	private static Map<String,String> sortTypes = Maps.newLinkedHashMap();
	static{
		sortTypes.put("auto", "自动(降序)");
		sortTypes.put("asc", "升序");
		subjectService = (SubjectClassHessianDao) new HessianUtil().getHessianProxyFactory(SubjectClassHessianDao.class, "subjectService");
	}
	/**主题分类列表**/
	@RequestMapping(method = RequestMethod.GET)
	public String sublist(@RequestParam(value="page",defaultValue="1") int pageNumber,
				          @RequestParam(value="page.size",defaultValue=Contants.PAGE_SIZE) int pageSize,
				          @RequestParam(value="sortType",defaultValue="asc") String sortType,
				          Model model,ServletRequest request){
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<SubjectClass> lists = subjectService.getSubjectClass(searchParams, pageNumber, pageSize,"seq",sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("subjects", lists);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "gxph/subject/list";
	}
	
	
	/**新增主题分类 页面跳转**/
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(ModelMap model){
		model.addAttribute("action", "create");
		return "gxph/subject/form" ;
	}
	
	/**更新主题分类 页面跳转**/
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(@PathVariable("id")String id,ModelMap model){
		try {
			SubjectClass sub=subjectService.getSub(id);
			model.addAttribute("action", "update");
			model.addAttribute("subject", sub);
			List<ApFileEnclosure> list = apFileEnclosureService.findByObjId(id);
			if(null!=list&&list.size()>0){
				sub.setApFileEnclosure(list.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gxph/subject/form" ;
	}
	/**删除主题分类**/
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable("id")String id,RedirectAttributes redirectAttributes){
		try {
			subjectService.deleteSub(id);
			redirectAttributes.addFlashAttribute("message", "删除主题成功");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "删除主题失败");
			e.printStackTrace();
		}
		
		return "redirect:/subject";
	}
	
	/**新增主题分类**/
	@RequestMapping(method = RequestMethod.POST,value="create")
	public String saveCreate(SubjectClass subject,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		try {
			LoginUser lu = new UserType().getUserType();
			subject.setRecorduserid(lu.getRecorduserid());
			subject.setRecordusername(lu.getRecordusername());
			subject.setRecordtime(lu.getRecordtime());
			String subid = subjectService.add(subject);
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,subid,Contants.OBJ_TYPE_ZTFL,new ArrayList<String>());
			if(b){
				redirectAttributes.addFlashAttribute("message", "主题分类添加成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "主题分类添加失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "主题分类添加失败");
			e.printStackTrace();
		}
		return "redirect:/subject";
	}
	/**更新主题分类**/
	@RequestMapping(method = RequestMethod.POST,value="update")
	public String saveUpdate(SubjectClass subject,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		LoginUser lu = new UserType().getUserType();
		subject.setRecorduserid(lu.getRecorduserid());
		subject.setRecordusername(lu.getRecordusername());
		subject.setRecordtime(lu.getRecordtime());
		String subid = subjectService.add(subject);
		try {
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,subid,Contants.OBJ_TYPE_ZTFL,new ArrayList<String>());
			if(b){
				redirectAttributes.addFlashAttribute("message", "主题分类编辑成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "主题分类编辑失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "主题分类编辑失败");
			e.printStackTrace();
		}
		return "redirect:/subject";
	}
}
