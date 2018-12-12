package close.gxph.bunny.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import close.gxph.bunny.entity.Order;
import close.gxph.bunny.repository.OrderHessianDao;
import close.gxph.bunny.util.DateUtil;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.core.constant.Contants;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * Created with IntelliJ IDEA.
 * Author: g
 * Date: 2013-10-09 14:39spoolService
 * Function: Spring定时任务管理
 */
@Component("orderspoolService")
public class ScheduledTaskManager {
    /**
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下）
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
     */

    /**
     * 定时5个工作日自动结算中
     */
    public static void updateOrderStep() {
    	OrderHessianDao orderService = (OrderHessianDao) new HessianUtil().getHessianProxyFactory(OrderHessianDao.class, "orderService");
    	Map<String, Object> searchParams = new TreeMap<String,Object>();
    	searchParams.put("EQ_step", "2");
    	Date d = DateUtil.getNextDayDates(new Date(), -Contants.ORDER_DAY);
    	String sdate=(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(d);  
    	Timestamp startTime = Timestamp.valueOf(sdate);//转换时间字符串为Timestamp
    	System.out.println(startTime);
    	searchParams.put("LTE_odate",startTime);
    	List<Order> orderpage = orderService.findOrderByCondition(searchParams);
    	for (Order order : orderpage) {
    		order.setStep("3");
    		orderService.updateOrder(order);
    		
		}
    }
    
    public static void main(String[] args) {
    	updateOrderStep();
	}
}
