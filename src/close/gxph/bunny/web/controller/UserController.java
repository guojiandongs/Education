package close.gxph.bunny.web.controller;

import java.io.IOException;
import java.sql.Timestamp;
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

import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.repository.UserHessianDao;
import close.gxph.bunny.repository.UsinessesHessianDao;
import close.gxph.bunny.service.UserService;
import close.gxph.bunny.service.UsinessesService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.repository.CoreUserRoleHessianDao;

import com.google.common.collect.Maps;
/**
 * 系统用户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	/*@Autowired
	private UserService userService ;*/
	UserHessianDao userService = (UserHessianDao) new HessianUtil().getHessianProxyFactory(UserHessianDao.class, "userService");
	/*@Autowired
	private UsinessesService usinessesService ;*/
	UsinessesHessianDao usinessesService = (UsinessesHessianDao) new HessianUtil().getHessianProxyFactory(UsinessesHessianDao.class, "usinessesService");
	private static Map<String,String> sortTypes = Maps.newLinkedHashMap();
	static{
		sortTypes.put("auto", "自动(降序)");
		sortTypes.put("asc", "升序");
	}
	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value="page" ,defaultValue="1") int pageNumber ,
			@RequestParam(value="page.size" ,defaultValue="15") int pageSize,
			@RequestParam(value="sortType",defaultValue="auto") String sortType,
			Model model ,ServletRequest request){
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request,"search_" );
		Page<User> lists = userService.getAllUsers(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("users", lists);
		//将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "gxph/user/list";
	}
	@RequestMapping(method = RequestMethod.GET,value="delete/{id}")
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes){
		User user = userService.getOne(id);
		if(!(user.getCode()).equals("admin")){
			userService.deleteUser(id);
		}
		redirectAttributes.addFlashAttribute("message", "删除用户成功");
		return "redirect:/user" ;
	}
	@RequestMapping(method = RequestMethod.GET,value="create")
	public String create(ModelMap model){
		model.addAttribute("action", "create");
		return "gxph/user/form" ;
	}
	@RequestMapping(method = RequestMethod.POST,value="create")
	public String create(User newUser,RedirectAttributes redirectAttributes){
		newUser.setCreatedate(new Timestamp(System.currentTimeMillis()));
		userService.addUser(newUser);
		redirectAttributes.addFlashAttribute("message", "新增用户成功");
		return "redirect:/user" ;
	}
	@RequestMapping(method= RequestMethod.GET, value="update/{id}")
	public String update(@PathVariable("id") String id,ModelMap model){
		model.addAttribute("action", "update");
		model.addAttribute("user", userService.getOne(id));
		return "gxph/user/form" ;
	}
	@RequestMapping(method = RequestMethod.POST, value="update")
	public String update(User user,RedirectAttributes redirectAttributes){
		User us = userService.getOne(user.getId());
		user.setCreatedate(us.getCreatedate());
		userService.addUser(user);
		redirectAttributes.addFlashAttribute("message", "用户编辑成功");
		return "redirect:/user" ;
	}
	/*@RequestMapping(method = RequestMethod.GET,value="findname")
    public void findname(HttpServletResponse response){
    	List<User> users = userService.listAll();
    	List<Map<String,String>> lists = new ArrayList<Map<String,String>>();
    	for(int i = 0;i<users.size();i++){
    		Map map = new HashMap();
    		map.put("id", users.get(i).getId());
    		map.put("name", users.get(i).getName());
    		lists.add(map);
    	}
    	JSONArray arr = JSONArray.fromObject(lists);
    	System.out.println(arr.toString());
    	try{
    		response.getWriter().write(arr.toString());
    	}catch(IOException e){
    	    e.printStackTrace();
    	}
    }*/
	
	// 验证用户名是否存在
		@RequestMapping(value = "/checkUsername")
		@ResponseBody
		public void  checkCode(@RequestParam(value="username" ,defaultValue="") String username,
				@RequestParam(value="name" ,defaultValue="") String name,HttpServletResponse response) {
			try {
				String s = "";
				if(!username.equals(name)){
					User user = userService.checkname(username);
					if(user != null&&user.getCode()!=null){
						s = "1";
						System.out.println("Sorry，该用户已被用！");
					} else {
						Usinesses us = usinessesService.checkname(username);
						if(us != null&&us.getUsername()!=null){
							s = "1";
							System.out.println("Sorry，该用户已被用！");
						}else{
							System.out.println("恭喜你，可以使用！！");
							s = "0";
						}
					}
				}else{
					s = "0";
				}
			//JSONObject str =JSONObject.fromObject(s);
			response.getWriter().print(s.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
