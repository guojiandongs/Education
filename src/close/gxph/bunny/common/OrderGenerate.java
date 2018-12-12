package close.gxph.bunny.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/***
 * 订单号
 * @author g
 *
 */
public class OrderGenerate {

	private static String prefix="LTT";
	private static long suffix=0;
	private static String lastDate="";
	
	public static String getOrderid(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date_str=sdf.format(date);
		Random r=new Random();
		int random=0;
		while(random<1000){
			random=r.nextInt(9999);
		}
		if(!lastDate.equals(date_str)){
			suffix=0;
		}
		lastDate=date_str;
		suffix++;
		return prefix+lastDate+random+suffix;
	}
	public static void main(String[] args) {
		String s=getOrderid(new Date());
		System.out.println(s);
	}
	
}