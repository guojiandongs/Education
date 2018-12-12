package close.gxph.bunny.web.controller;

import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.caucho.hessian.client.HessianProxyFactory;
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
import close.gxph.bunny.entity.LoginUser;
import close.gxph.bunny.repository.AdPicHessianDao;
import close.gxph.bunny.repository.ApFileEnclosureDao;
import close.gxph.bunny.repository.ApFileEnclosureHessianDao;
import close.gxph.bunny.service.ApFileEnclosureService;
import close.gxph.bunny.service.DictService;
import close.gxph.bunny.service.AdPicService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.bunny.util.UserType;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.constant.Contants;
/**
 * 广告(幻灯片)
 * @author g
 *
 */
@Controller
@RequestMapping(value = "/ad")
public class AdPicController {
	/*@Autowired
	private AdPicService adPicService;*/
	@Autowired
	private ApFileEnclosureService apFileEnclosureService;
	private static AdPicHessianDao adPicService;
	//private static ApFileEnclosureHessianDao apFileEnclosureService;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动(降序)");
		sortTypes.put("asc", "升序");
		adPicService = (AdPicHessianDao) new HessianUtil().getHessianProxyFactory(AdPicHessianDao.class, "adService");
		//apFileEnclosureService = (ApFileEnclosureHessianDao) new HessianUtil().getHessianProxyFactory(ApFileEnclosureHessianDao.class, "apFileEnclosureService");
	}
	
	// 打开列表
	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Contants.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "asc") String sortType,
			Model model, ServletRequest request) throws MalformedURLException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
        
		//AdPicHessianDao hello = (AdPicHessianDao) factory.create(AdPicHessianDao.class, url);
		Page<AdPic> lists = adPicService.getAdPics(searchParams, pageNumber, pageSize, "seq", sortType);
		/*Page<AdPic> lists = adPicService.getAdPics(searchParams, pageNumber,
				pageSize, "seq",sortType);*/
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("ads", lists);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_")); 
		return "gxph/ad/list";
	}

	//删除
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		adPicService.deleteAdPic(id);
		redirectAttributes.addFlashAttribute("message", "删除广告成功");
		return "redirect:/ad";
	}

	//跳转到添加
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(ModelMap model) {
		model.addAttribute("action", "create");
		/*List<Dict> dicts=dictService.getDicts("ad_type");
		model.addAttribute("dicts", dicts);*/
		return "gxph/ad/form";
	}

	//添加
	@RequestMapping(method = RequestMethod.POST, value = "create")
	public String create(HttpServletRequest request,HttpServletResponse response,AdPic newAdPic, RedirectAttributes redirectAttributes) {
		try {
			LoginUser lu = new UserType().getUserType();
			newAdPic.setRecorduserid(lu.getRecorduserid());
			newAdPic.setRecordusername(lu.getRecordusername());
			newAdPic.setRecordtime(lu.getRecordtime());
			String adid = adPicService.addAdPic(newAdPic);
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,adid,Contants.OBJ_TYPE_AD,new ArrayList<String>());
			if(b){
				redirectAttributes.addFlashAttribute("message", "广告添加成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "广告添加失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "广告添加失败");
			e.printStackTrace();
		}
		return "redirect:/ad";
	}

	//跳转到修改
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("action", "update");
		/*List<Dict> dicts=dictService.getDicts("ad_type");
		model.addAttribute("dicts", dicts);*/
		AdPic adPic = null;
		try {
			adPic = adPicService.getAdPic(id);
			List<ApFileEnclosure> list = apFileEnclosureService.findByObjId(id);
			if(null!=list&&list.size()>0){
				adPic.setApFileEnclosure(list.get(0));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("ad",adPic);
		return "gxph/ad/form";
	}

	//修改
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public String update(HttpServletRequest request,HttpServletResponse response,AdPic AdPic, RedirectAttributes redirectAttributes) {
		try {
			LoginUser lu = new UserType().getUserType();
			AdPic.setRecorduserid(lu.getRecorduserid());
			AdPic.setRecordusername(lu.getRecordusername());
			AdPic.setRecordtime(lu.getRecordtime());
			String adid = adPicService.alterAdPic(AdPic);
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,adid,Contants.OBJ_TYPE_AD,new ArrayList<String>());
			if(b){
				redirectAttributes.addFlashAttribute("message", "广告编辑成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "广告编辑失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "广告编辑失败");
			e.printStackTrace();
		}
		return "redirect:/ad";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "check")
	@ResponseBody
	public String check(@RequestParam("seq") Integer seq) {
		AdPic ad=adPicService.getAdBySeq(seq);
		if(null==ad) {
			return "true";
		} else {
			return "false";
		}
	}
	
}
