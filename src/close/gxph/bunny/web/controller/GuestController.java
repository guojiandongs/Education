package close.gxph.bunny.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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

import close.gxph.bunny.entity.Guest;
import close.gxph.bunny.entity.User;
import close.gxph.bunny.repository.AdPicHessianDao;
import close.gxph.bunny.repository.GuestHessianDao;
import close.gxph.bunny.service.GuestService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.constant.Contants;

import com.google.common.collect.Maps;
/**
 * 游客管理
 * @author g
 *
 */
@Controller
@RequestMapping(value = "/guest")
public class GuestController {
	/*@Autowired
	private GuestService guestService;*/
	private static GuestHessianDao guestService;

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动(降序)");
		sortTypes.put("asc", "升序");
		guestService = (GuestHessianDao) new HessianUtil().getHessianProxyFactory(GuestHessianDao.class, "guestServiceService");
	}

	// 打开列表
	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Contants.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<Guest> lists = guestService.getGuests(searchParams, pageNumber,
				pageSize, sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("guests", lists);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "gxph/guest/list";
	}

	//删除
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		guestService.deleteGuest(id);
		redirectAttributes.addFlashAttribute("message", "删除直客成功");
		return "redirect:/guest";
	}

	//跳转到添加页面
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(ModelMap model) {
		model.addAttribute("action", "create");
		return "gxph/guest/form";
	}

	//跳转到修改页面
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public String update(Guest guest, RedirectAttributes redirectAttributes) {
		guestService.alterGuest(guest);
		redirectAttributes.addFlashAttribute("message", "直客编辑成功");
		return "redirect:/guest";
	}
	
	//判断用户名是否存在
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET,value="findname")
	public void findname(HttpServletResponse response){
		List<Guest> guests =  guestService.findAll();
		List<Map<String,String>> lists = new ArrayList<Map<String, String>>();
		for(int i=0;i<guests.size();i++){
			Map map=new HashMap();
			map.put("id", guests.get(i).getId());
			map.put("name",guests.get(i).getName());
			lists.add(map);
		}
		JSONArray arr=JSONArray.fromObject(lists);
		//System.out.println(arr.toString());
		
		try {
			response.getWriter().write(arr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 验证用户名是否存在
		@RequestMapping(value = "/checkUsername")
		@ResponseBody
		public void  checkCode(
				@RequestParam(value="username" ,defaultValue="") String username,HttpServletResponse response) {
			List<Guest> guests = guestService.findAllByUsername(username);
			String s = "";
			try {
			if(guests != null && guests.size()>0){
				s = "Sorry，该用户已被用！";
				System.out.println("Sorry，该用户已被用！");
			} else {
				System.out.println("恭喜你，可以使用！！");
				s = "恭喜你，可以使用！！";
			}
			//JSONObject str =JSONObject.fromObject(s);
			response.getWriter().print(s.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
