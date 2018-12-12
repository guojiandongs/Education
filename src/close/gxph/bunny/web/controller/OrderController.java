package close.gxph.bunny.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import close.gxph.bunny.entity.Order;
import close.gxph.bunny.repository.AdPicHessianDao;
import close.gxph.bunny.repository.GuestHessianDao;
import close.gxph.bunny.repository.OrderHessianDao;
import close.gxph.bunny.service.GuestService;
import close.gxph.bunny.service.OrderService;
import close.gxph.bunny.util.DateUtil;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.common.modules.web.Servlets;

/**
 * 订单管理
 * @author g
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	/*@Autowired
	private OrderService orderService;*/
	private static OrderHessianDao orderService;
	
	/*@Autowired
	private GuestService guestService;*/
	private static GuestHessianDao guestService;
	static {
		orderService = (OrderHessianDao) new HessianUtil().getHessianProxyFactory(OrderHessianDao.class, "orderService");
		guestService = (GuestHessianDao) new HessianUtil().getHessianProxyFactory(GuestHessianDao.class, "guestServiceService");
	}
	/**
	 * 自定义查询带分页
	 * @param pageNumber
	 * @param pageSize
	 * @param order
	 * @param sortBy
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = "15") int pageSize,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "sortby", required = false) String sortBy,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		String productname="";
		if(!searchParams.isEmpty()){
			String name = (String) searchParams.get("LIKE_product.name");
			if(!name.equals("")){
				productname = name;
			}
			String gtodate = (String) searchParams.get("GTE_odate");
			if(!gtodate.equals("")){
				Date d = DateUtil.getLastDayDate(DateUtil.formatDate(gtodate, "yyyy-MM-dd"));
				String odate = DateUtil.formatDateToStr(d, "yyyy-MM-dd");
				searchParams.put("GTE_odate",DateUtil.convertTimecode(odate));
				model.addAttribute("gteodate", gtodate);
			}
			String ltodate = (String) searchParams.get("LTE_odate");
			if(!ltodate.equals("")){
				searchParams.put("LTE_odate",DateUtil.convertTimecode(ltodate));
				model.addAttribute("lteodate", ltodate);
			}
		}
		
		try {
			Page<Order> lists = orderService.getOrders(searchParams,
					pageNumber, pageSize,"odate", "desc");
			model.addAttribute("orders", lists);
			model.addAttribute("productname", productname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "gxph/order/list";
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public String update(@PathVariable("id")String id,ModelMap model) {
		Order order=orderService.getOrderById(id);
		Object object=new Object();
		object=guestService.getGuest(order.getGuestid());
		model.addAttribute("action", "update");
		model.addAttribute("order", order);
		model.addAttribute("user", object);
		return "gxph/order/form";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/delete")
	public String delete(@PathVariable("id")String id,ModelMap model) {
		orderService.deleteOrderById(id);
		return "redirect:/order";
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/update")
	public String saveUpdate(Order order,ServletRequest request) {
		orderService.updateOrder(order);
		return "redirect:/order";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/detail")
	public String detail(@PathVariable("id")String id,ModelMap model) {
		try {
			Order order = orderService.getOrderById(id);
			model.addAttribute("order", order);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return "gxph/order/listdetail";
	}
}
