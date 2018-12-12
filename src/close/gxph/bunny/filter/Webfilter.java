package close.gxph.bunny.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import close.gxph.bunny.common.CommonDaoImplJPA;

public class Webfilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(filterConfig.getServletContext());
		CommonDaoImplJPA commonDao = wac.getBean(CommonDaoImplJPA.class);
		//EacheSecKillService eache = wac.getBean(EacheSecKillService.class);
		/*****************CacheManager***********start******************/
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String sql="select * from gxph_seckill where etime>?";
		if(null!=commonDao){
			List<Map<String, Object>> list=commonDao.execCommonSQL(sql, new Object[]{sdf.format(new Date())});
			if(null!=list&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map=list.get(i);
					String id=map.get("id").toString();
					Timestamp timestamp=(Timestamp) map.get("etime");
					Date date=new Date(timestamp.getTime());
					eache.addSecKill(id, date);
				}
			}
		}else{
			throw new RuntimeException("初始化失败！");
		}*/
		/**********************CacheManager-------------end*******************/
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res=(HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Origin,Authorization, X-Requested-With, Content-Type, Accept");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
	public static String readString(HttpServletRequest request){
		ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream();
		InputStream is=null;
		try {
			is=request.getInputStream();
			int   i=-1; 
			while((i=is.read())!=-1){ 
				baos.write(i); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(baos!=null)
					baos.close();
				if(is!=null)
					is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return baos.toString();
	}
}
