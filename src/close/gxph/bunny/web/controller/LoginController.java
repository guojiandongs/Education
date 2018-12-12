package close.gxph.bunny.web.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.repository.CoreResHessianDao;
import close.gxph.bunny.repository.UsinessesDao;
import close.gxph.bunny.service.UserService;
import close.gxph.bunny.service.UsinessesService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.common.util.Clock;
import close.gxph.core.common.util.StringX;
import close.gxph.core.constant.Contants;
import close.gxph.core.entity.CoreRole;
import close.gxph.core.entity.CoreRoleRes;
import close.gxph.core.entity.CoreUserRole;
//import close.gxph.core.entity.Auth;
import close.gxph.core.repository.CoreRoleHessianDao;
import close.gxph.core.repository.CoreUserRoleHessianDao;
import close.gxph.core.service.CoreUserRoleService;
/**
 * 登陆
 * @author g
 *
 */
@Controller
@SessionAttributes(value = { "menus", "username", "headimg","resources" })
public class LoginController {
	private static Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	private Clock clock = Clock.DEFAULT;
	@Autowired
	private UserService userService;
	@Autowired
	private UsinessesService usinessesService;
	/*@Autowired
	private CoreUserRoleService coreUserRoleService;*/
	CoreUserRoleHessianDao coreUserRoleService = (CoreUserRoleHessianDao) new HessianUtil().getHessianProxyFactory(CoreUserRoleHessianDao.class, "coreUserRoleService");
	CoreRoleHessianDao coreRoleService = (CoreRoleHessianDao) new HessianUtil().getHessianProxyFactory(CoreRoleHessianDao.class, "coreRoleService");
	// 打开登录页面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest req, ModelMap model) {
		String agent = req.getHeader("User-Agent");
		if (agent.contains("MSIE 6") || agent.contains("MSIE 7")
				|| agent.contains("MSIE 8"))
			model.addAttribute("message", "检测到您正在使用的浏览器不能正常使用本系统，请使用推荐浏览器");
		return "login";
	}

	// 登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "remember", defaultValue = "1") String remember,
			@RequestParam(value = "type", defaultValue = "") String inlineRadioOptions,
			HttpServletRequest req, HttpServletResponse response, ModelMap model) {
		User user = userService.getUser(username);
		Usinesses us = usinessesService.checkname(username);
		if (user == null) {
			if(us==null){
				model.addAttribute("message", "您输入的用户名不存在。");
				return "login";
			}else{
				if(!us.getStatus().equals("1")){
					model.addAttribute("message", "您的账户没有权限,请联系管理员。");
					return "login";
				}else{
					System.out.println(StringX.md5(password.getBytes()).toLowerCase());
					boolean b = us.getUpassword().equals(StringX.md5(password.getBytes()).toLowerCase());
					if(!b){
						model.addAttribute("message", "您输入的密码有误,请重新输入。");
						return "login";
					}
				}
			}
		}else{
			if (user.getStatus() != 1) {
				model.addAttribute("message", "您的账户没有权限,请联系管理员。");
				return "login";
			}else{
				boolean b = user.getPwd().equals(StringX.md5(password.getBytes()).toLowerCase());
				if(!b){
					model.addAttribute("message", "您输入的密码有误,请重新输入。");
					return "login";
				}
			}
		}
		
		try {
			SecurityUtils.getSubject().login(
					new UsernamePasswordToken(username, password));
			if (remember.equals("1")) {

				// 创建用户密码Cookie对象
				Cookie cookieCode = new Cookie("usercode", username);
				// 设置保存Cookie时间
				cookieCode.setMaxAge(30 * 24 * 60 * 60);
				// 添加到客户端
				response.addCookie(cookieCode);
				Cookie cookiePwd = new Cookie("password", password);
				cookiePwd.setMaxAge(30 * 24 * 60 * 60);
				response.addCookie(cookiePwd);
			} else {
				Cookie cookieCode = new Cookie("usercode", username);
				cookieCode.setMaxAge(30 * 24 * 60 * 60);
				response.addCookie(cookieCode);
				Cookie cookiePwd = new Cookie("password", "");
				cookiePwd.setMaxAge(30 * 24 * 60 * 60);
				response.addCookie(cookiePwd);
			}
			Cookie cookieRem = new Cookie("remember", remember);
			cookieRem.setMaxAge(30 * 24 * 60 * 60);
			response.addCookie(cookieRem);
			CoreRole cr = null;
			if(user!=null){
				model.addAttribute("resources", coreUserRoleService.loadResource(user.getId()));
				userService.alterUser(user);
				SecurityUtils.getSubject().getSession()
				.setAttribute(Contants.CURRENT_USER, user);
				CoreUserRole cur=coreUserRoleService.getCoreUserRoleByUserid(user.getId());
				if(cur!=null){
					cr = coreRoleService.findOne(cur.getRoleid());
				}
			}else{
				model.addAttribute("resources", coreUserRoleService.loadResource(us.getId()));
				usinessesService.alterUsinesses(us);
				SecurityUtils.getSubject().getSession()
				.setAttribute(Contants.CURRENT_US, us);
				CoreUserRole cur=coreUserRoleService.getCoreUserRoleByUserid(us.getId());
				if(cur!=null){
					cr = coreRoleService.findOne(cur.getRoleid());
				}
			}
			
			SecurityUtils.getSubject().getSession()
			.setAttribute(Contants.ROLE_CODE, cr);
//			currenter.setLastlogindate(clock.getCurrentTime());
//			if (currenter.getLogintimes() == null)
//				currenter.setLogintimes(1);
//			else
//				currenter.setLogintimes(currenter.getLogintimes() + 1);
//			currenter.setLastloginip(req.getHeader("X-Real-IP"));
			/*logger.info("用户：" + currenter.getCode() + "--"
					+ currenter.getName() + "，登录成功，登录IP："
					+ req.getHeader("X-Real-IP"));*/
			return "redirect:/main";
		} catch (AuthenticationException e) {
			model.addAttribute("message", "您输入的用户密码错误");
			logger.info("用户：" + username + "，登录失败，登录IP："
					+ req.getHeader("X-Real-IP"));
			return "login";
		}
	}
	// 退出登录
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletResponse response) {
		SecurityUtils.getSubject().logout();
		Cookie cookiePwd = new Cookie("password", "");
		cookiePwd.setMaxAge(30 * 24 * 60 * 60);
		response.addCookie(cookiePwd);
		return "redirect:/login";
	}

	// 未授权页面
	@RequestMapping(value = "/unauth", method = RequestMethod.GET)
	public String unauth() {
		return "unauth";
	}

	// 忘记密码界面
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot() {
		return "forgot";
	}

	// 忘记密码提交
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgot(
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			HttpServletRequest req, ModelMap model,
			RedirectAttributes redirectAttributes) {
		User user = userService.getUserByMobile(mobile);
		if (user == null) {
			model.addAttribute("message", "您输入手机号有误");
			return "forgot";
		} else {
			userService.resetPassword(user);
			redirectAttributes.addFlashAttribute("message", "新密码已成功发送到"
					+ mobile);
			return "redirect:/notify";
		}
	}

	// 消息提示界面
	@RequestMapping(value = "/notify", method = RequestMethod.GET)
	public String noti() {
		return "notify";
	}

	// 打开主页
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest req, ModelMap model) {
		String usercode = (String) SecurityUtils.getSubject().getPrincipal();
//		List<Auth> menus = userService.getUserMenus(usercode);
		// List<ProjectVO> projects = authService.getSubordinateProjects(sui
		// .getUserCode());
//		model.addAttribute("menus", menus);
//		SecurityUtils.getSubject().getSession().setAttribute("resources", coreUserRoleService.loadResource(userService.getUser(usercode).getId()));
		HttpSession session=req.getSession(true);
		session.setMaxInactiveInterval(30*60);
		User user = userService.getUser(usercode);
		List<CoreRoleRes> list = new ArrayList<CoreRoleRes>();
		String username="";
		if(user!=null){
			list = coreUserRoleService.loadResource(user.getId());
			username = user.getName();
		}else{
			Usinesses us = usinessesService.checkname(usercode);
			list = coreUserRoleService.loadResource(us.getId());
			username = us.getName();
		}
		session.setAttribute("resources",list);
		model.addAttribute("resources", list);
		model.addAttribute("username", username);
		/*if (!StringX.nullity(userService.getUser(usercode).getHeadimg())){
			model.addAttribute("headimg", userService.getUser(usercode)
					.getHeadimg());
		}else model.addAttribute("headimg", "");*/
		return "main";
	}
	
	@RequestMapping(value = "/portal", method = RequestMethod.GET)
	public String portal() {
		return "portal";
	}
	
}
