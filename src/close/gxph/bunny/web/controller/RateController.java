package close.gxph.bunny.web.controller;

import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import close.gxph.bunny.entity.Dict;
import close.gxph.bunny.entity.Rate;
import close.gxph.bunny.repository.DictHessianDao;
import close.gxph.bunny.repository.RateHessianDao;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.constant.Contants;
/**
 * 费率
 * @author g
 *
 */
@Controller
@RequestMapping(value = "/rate")
public class RateController {
	private static RateHessianDao rateService;
	private static DictHessianDao dictService;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动(降序)");
		sortTypes.put("asc", "升序");
		rateService = (RateHessianDao) new HessianUtil().getHessianProxyFactory(RateHessianDao.class, "rateService");
		dictService = (DictHessianDao) new HessianUtil().getHessianProxyFactory(DictHessianDao.class, "dictService");
	}
	
	// 打开列表
	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Contants.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "asc") String sortType,
			Model model, ServletRequest request) throws MalformedURLException {
		List<Rate> lists = rateService.listAll();
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("ratelist", lists);
		return "gxph/rate/list";
	}

	//删除
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		rateService.deleteRate(id);
		redirectAttributes.addFlashAttribute("message", "删除费率成功");
		return "redirect:/rate";
	}

	//跳转到添加
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(ModelMap model) {
		model.addAttribute("action", "create");
		List<Dict> dicts=dictService.getDicts("rate_name");
		model.addAttribute("dicts", dicts);
		return "gxph/rate/form";
	}

	//添加
	@RequestMapping(method = RequestMethod.POST, value = "create")
	public String create(HttpServletRequest request,HttpServletResponse response,Rate rate, RedirectAttributes redirectAttributes) {
		try {
			rateService.addRate(rate);
			redirectAttributes.addFlashAttribute("message", "费率添加成功");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "费率添加失败");
			e.printStackTrace();
		}
		return "redirect:/rate";
	}

	//跳转到修改
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("action", "update");
		List<Dict> dicts=dictService.getDicts("ad_type");
		model.addAttribute("dicts", dicts);
		Rate rate = null;
		try {
			rate = rateService.getRate(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("rate",rate);
		return "gxph/rate/form";
	}

	//修改
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public String update(HttpServletRequest request,HttpServletResponse response,Rate rate, RedirectAttributes redirectAttributes) {
		try {
			rateService.alterRate(rate);
			redirectAttributes.addFlashAttribute("message", "费率编辑成功");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "费率编辑失败");
			e.printStackTrace();
		}
		return "redirect:/ad";
	}
}
