package close.gxph.bunny.web.controller;

import java.util.Date;
import java.util.Map;
import javax.servlet.ServletRequest;

import org.jsoup.Jsoup;
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

import close.gxph.bunny.entity.LoginUser;
import close.gxph.bunny.entity.Order;
import close.gxph.bunny.entity.OrderReturn;
import close.gxph.bunny.entity.WXRefund;
import close.gxph.bunny.repository.GuestHessianDao;
import close.gxph.bunny.repository.OrderHessianDao;
import close.gxph.bunny.repository.OrderReturnHessianDao;
import close.gxph.bunny.service.GuestService;
import close.gxph.bunny.service.OrderReturnService;
import close.gxph.bunny.service.OrderService;
import close.gxph.bunny.util.DateUtil;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.bunny.util.OrderUtil;
import close.gxph.bunny.util.UserType;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.constant.Contants;

/**
 * 退单
 * @author g
 */
@Controller
@RequestMapping(value = "/orderreturn")
public class OrderReturnController {
	/*@Autowired
	private OrderReturnService orderReturnService;*/
	private static OrderReturnHessianDao orderReturnService;
	static {
		orderReturnService = (OrderReturnHessianDao) new HessianUtil().getHessianProxyFactory(OrderReturnHessianDao.class, "orderReturnService");
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
			String name = (String) searchParams.get("LIKE_order.product.name");
			if(!name.equals("")){
				productname = name;
			}
			String gtodate = (String) searchParams.get("GTE_returntime");
			if(!gtodate.equals("")){
				Date d = DateUtil.getLastDayDate(DateUtil.formatDate(gtodate, "yyyy-MM-dd"));
				String odate = DateUtil.formatDateToStr(d, "yyyy-MM-dd");
				searchParams.put("GTE_returntime",DateUtil.convertTimecode(odate));
				model.addAttribute("gteodate", gtodate);
			}
			String ltodate = (String) searchParams.get("LTE_returntime");
			if(!ltodate.equals("")){
				searchParams.put("LTE_returntime",DateUtil.convertTimecode(ltodate));
				model.addAttribute("lteodate", ltodate);
			}
		}
		
		try {
			Page<OrderReturn> lists = orderReturnService.getOrderreturns(searchParams,
					pageNumber, pageSize,"odate", "desc");
			model.addAttribute("orderreturnlist", lists);
			model.addAttribute("productname", productname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "gxph/orderreturn/list";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return  查看详情
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/detail")
	public String detail(@PathVariable("id")String id,ModelMap model) {
		try {
			OrderReturn orderr = orderReturnService.getOrderReturnById(id);
			model.addAttribute("orderrrturn", orderr);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return "gxph/orderreturn/listdetail";
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
	@RequestMapping(method = RequestMethod.GET,value="/returnvalidate")
	public String returnvalidate(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = "15") int pageSize,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "sortby", required = false) String sortBy,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		String productname="";
		if(!searchParams.isEmpty()){
			String name = (String) searchParams.get("LIKE_order.product.name");
			if(!name.equals("")){
				productname = name;
			}
			String gtodate = (String) searchParams.get("GTE_returntime");
			if(!gtodate.equals("")){
				Date d = DateUtil.getLastDayDate(DateUtil.formatDate(gtodate, "yyyy-MM-dd"));
				String odate = DateUtil.formatDateToStr(d, "yyyy-MM-dd");
				searchParams.put("GTE_returntime",DateUtil.convertTimecode(odate));
				model.addAttribute("gteodate", gtodate);
			}
			String ltodate = (String) searchParams.get("LTE_returntime");
			if(!ltodate.equals("")){
				searchParams.put("LTE_returntime",DateUtil.convertTimecode(ltodate));
				model.addAttribute("lteodate", ltodate);
			}
		}
		
		try {
			searchParams.put("EQ_state", Contants.CHECK_STATE_NO);
			Page<OrderReturn> lists = orderReturnService.getOrderreturns(searchParams,
					pageNumber, pageSize,"odate", "desc");
			model.addAttribute("orderreturnlist", lists);
			model.addAttribute("productname", productname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "gxph/orderreturn/returnvalidatelist";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return  查看详情
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/orderreturnvalidate")
	public String orderreturnvalidate(@PathVariable("id")String id,ModelMap model) {
		try {
			OrderReturn orderr = orderReturnService.getOrderReturnById(id);
			model.addAttribute("orderrrturn", orderr);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return "gxph/orderreturn/returnvalidatedetail";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return  审核退单
	 */
	@RequestMapping(method = RequestMethod.POST, value = "validatesubmits/{id}")
	public String validatesubmits(@PathVariable("id")String id,ModelMap model,RedirectAttributes redirectAttributes,@RequestParam(value = "mark") String mark) {
		try {
			if(mark.equals("0")){
				OrderReturn orderr = orderReturnService.getOrderReturnById(id);
				orderr.setState("1");
				WXRefund refund= new WXRefund();
				refund.setAppid(Contants.appid);
				refund.setMch_id(Contants.PAY_USINESSES);
				refund.setOut_refund_no(orderr.getTno());
				refund.setOut_trade_no(orderr.getNotest());
				refund.setOp_user_id(Contants.PAY_USINESSES);
				refund.setRefund_fee("1");
				refund.setTotal_fee("1");
				refund.setRefund_fee_type("CNY");
				refund.setNonce_str(OrderUtil.CreateNoncestr());
				refund.setPartnerKey(Contants.PAY_KEY);
					
			
				String result = null;
				result = refund.refund(Contants.PAY_USINESSES);
				String return_code = Jsoup.parse(result).select("return_code").html();
				if(return_code.equals("SUCCESS")){
					LoginUser lu = new UserType().getUserType();
					/*orderr.setRecorduserid(lu.getRecorduserid());
					orderr.setRecordusername(lu.getRecordusername());
					orderr.setRecordtime(lu.getRecordtime());*/
					orderReturnService.alterOrderReturn(orderr);
				}else{
					String err_code_des = Jsoup.parse(result).select("err_code_des").html();
					redirectAttributes.addFlashAttribute("message", "退款失败！");
					System.out.println("错误原因======="+err_code_des);
				}
				//此处添加支付成功后，支付金额和实际订单金额是否等价，防止钓鱼
			    System.out.println(result);
			    redirectAttributes.addFlashAttribute("message", "退款成功！");
			}else{
				redirectAttributes.addFlashAttribute("message", "审核不通过！");
			}
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "退款失败！");
			e.printStackTrace();	
		}
		return "redirect:/orderreturn/returnvalidate";
	}
}
