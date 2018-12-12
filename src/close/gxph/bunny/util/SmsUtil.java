package close.gxph.bunny.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;



/**
 * 发送短信
 * @author 东
 *
 */
public class SmsUtil {
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	public String sendsms(String phones,String contents) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);

		// client.getParams().setContentCharset("GBK");
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType",
				"application/x-www-form-urlencoded;charset=UTF-8");

		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);

		System.out.println(mobile_code);
		//正式模板
//		String content = new String("您的验证码是：" + mobile_code
//				+ "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！");
		//测试模板
		String content = new String("您的验证码是："+mobile_code+"。请不要把验证码泄露给其他人。");
		/*String content = new String(contents);*/
		System.out.println(phones + "电话号");
		NameValuePair[] data = {// 提交短信
				new NameValuePair("account", "cf_liu_8709@@@@@"),
				new NameValuePair("password", "qi0620@@@@@"), // 密码可以使用明文密码或使用32位MD5加密
				// new NameValuePair("password",
				// util.StringUtil.MD5Encode("密码")),
				new NameValuePair("mobile", phones),//手机号码
				new NameValuePair("content", content), };
		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			// System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
//			return mobile_code+"";
			if ("2".equals(code)) {
				return mobile_code+"";
			}else{
				return "failure";
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failure";
	}
}
