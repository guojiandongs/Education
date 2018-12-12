package close.gxph.bunny.util;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianRemoteObject;

import close.gxph.bunny.repository.AdPicHessianDao;
import close.gxph.core.constant.Contants;


/**
 * 日期提供者，使用它而不是直接取得系统时间，方便测试。
 * 
 * @author calvin
 */
public class HessianUtil {

	public Object getHessianProxyFactory(Class<?> api,String url){
		String urls = Contants.HESSIAN_URL+url; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        factory.setOverloadEnabled(true);
        Object obj = null;
        try {
			 obj = factory.create(api, urls);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        return obj;
	}

}
