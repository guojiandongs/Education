package close.gxph.bunny.web.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.bunny.service.ApFileEnclosureService;
import close.gxph.bunny.util.FileOperateUtil;
import close.gxph.bunny.util.StrUtil;
import close.gxph.core.constant.Contants;

/**
 * (后台)图片、视频、文件上传  文件管理
 */

@Controller
@RequestMapping(value="/apFileEnclosure")
public class FileEnclosureController {
	@Autowired
	private ApFileEnclosureService apFileEnclosureService;
	
	/**
	 * 找到所有的文件列表，带分页
	 * @param page
	 * @param pageSize
	 * @param orderType
	 * @param sortBy
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/findallbyparam")
	public String findAllByParam(
			Model model, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value = "orderType",defaultValue="desc") String orderType,
			@RequestParam(value = "sortBy", defaultValue = "saveTime") String sortBy){
		
		try {
			Map<String,Object> searchParams=new TreeMap<String,Object>();
			searchParams.put("EQ_deleteState", Contants.DEL_STATE_NO);
			searchParams.put("EQ_objType", Contants.FILE_NEWS_SLIDE);
			Page<ApFileEnclosure> pages = apFileEnclosureService.findToaFileEnclosures(searchParams,page, pageSize, sortBy,orderType);
			model.addAttribute("pages", pages);
			return "fileEnclosure/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "fileEnclosure/list";
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/delApFileEnclosure/{id}")
	public @ResponseBody String delApFileEnclosure(Model model, @PathVariable(value = "id") String id,RedirectAttributes redirectAttributes){
		
		try {
			Map<String,Object> searchParams=new TreeMap<String,Object>();
			if(!StrUtil.isEmpty(id)){
				ApFileEnclosure ApFileEnclosure = apFileEnclosureService.findToaFileEnclosure(id);
				ApFileEnclosure = apFileEnclosureService.delFileEnclosure(ApFileEnclosure);
				redirectAttributes.addFlashAttribute("message", "删除成功");
				return "{\"message\":\"success\"}";
			}
			redirectAttributes.addFlashAttribute("message", "删除失败");
			return "{\"message\":\"failure\"}";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", "failure");
			model.addAttribute("message", "请求失败");
			return "{\"message\":\"failure\"}";
		}
	}
	
	/**
	 * 找到一条文件的详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/findDetailApFileEnclosure/{id}")
	public String findDetailApFileEnclosure(Model model, @PathVariable(value = "id") String id){
		
		try {
		if(!StrUtil.isEmpty(id)){
				ApFileEnclosure ApFileEnclosure = apFileEnclosureService.findToaFileEnclosure(id);
				model.addAttribute("action", "altApFileEnclosure");
				model.addAttribute("ApFileEnclosure",ApFileEnclosure);
				model.addAttribute("result", "success");
				model.addAttribute("message", "查找成功");
				return "fileEnclosure/form";
			}
			model.addAttribute("result", "failure");
			model.addAttribute("message", "查找失败");
			return "fileEnclosure/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", "failure");
			model.addAttribute("message", "请求失败");
			return "error/unauth";
		}
	}
	/**
	 * 修改(添加)文件
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,value="/altApFileEnclosure")
	public String altApFileEnclosure(RedirectAttributes redirectAttribute, ApFileEnclosure apFileEnclosure,
			HttpServletResponse response,File file){
		try {
				apFileEnclosure = apFileEnclosureService.updToaFileEnclosure(apFileEnclosure);
				redirectAttribute.addAttribute("message", "修改成功！");
				return "redirect:/apFileEnclosure/findallbyparam";	
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttribute.addAttribute("message", "请求失败！");
			return "error/unauth";
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET,value="/create")
	public String create(ModelMap model){
		model.addAttribute("action", "addApFileEnclosure");
		return "fileEnclosure/form" ;
	}
	/**
	 * 文件的上传
	 * @param request
	 * @param objType
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(method=RequestMethod.POST, value="/upload")
    public void upload(HttpServletRequest request,
    		 HttpServletResponse response,@RequestParam(value = "objType") String objType) throws IOException{
        Map<String,String> map = new HashMap<String, String>();
       
	        CommonsMultipartResolver resolver = new CommonsMultipartResolver(
	                request.getServletContext());
	        try {
	        if (resolver.isMultipart(request)) {
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	            // 取得request中的所有文件名
	                map = FileOperateUtil.apupload(multiRequest,objType);
	                //添加文件
	                if(map.get("result") != null && map.get("result").equals("success")){
	                	ApFileEnclosure  apFileEnclosure = new ApFileEnclosure();
	        	        apFileEnclosure.setObjType(Contants.FILE_NEWS_SLIDE);
	        	        apFileEnclosure.setFileType(map.get("fileType"));
	        	        apFileEnclosure.setFileName(map.get("fileOriginName"));
	        	        apFileEnclosure.setPathAbs(map.get("fileAbsolutePath"));
	        	        apFileEnclosure.setPathRel(map.get("fileRelativePath"));
	        	        apFileEnclosure.setSaveTime(new Timestamp(System.currentTimeMillis()));
	        	        apFileEnclosure.setDeleteState(Contants.DEL_STATE_NO);
	        	        apFileEnclosureService.updToaFileEnclosure(apFileEnclosure); 
	    		        response.getWriter().write("success");
	    		        response.getWriter().close();
	                }else{
	                    response.getWriter().write("failure");
	                    response.getWriter().close();
	                }
	                
	        }	
		    } catch (IOException e) {
		        e.printStackTrace();
		        response.getWriter().write("failure");
		        response.getWriter().close();
		    }      
	}
	public enum State {
		OK(200, "上传成功"),
		ERROR(500, "上传失败"),
		OVER_FILE_LIMIT(501, "超过上传大小限制"),
		NO_SUPPORT_EXTENSION(502, "不支持的扩展名");

		private int code;
		private String message;
		private State(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}

	}

	/**
	 * 返回结果函数
	 * @param response
	 * @param state
	 */
	private static final String encode = "UTF-8";
	private void responseMessage(HttpServletResponse response, State state) {
		
		response.setCharacterEncoding(encode);
		response.setContentType("text/html; charset=" + encode);
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write("<script>");
			writer.write("window.parent.fileUploadCallBack({\"code\":" + state.getCode() +",\"message\":\"" + state.getMessage()+ "\"});");
			writer.write("</script>");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			//logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
	/*public static String upload(String httpurl, String fileName, InputStream inputStream) {
        String result = "";
        try {
            String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线
            URL url = new URL(httpurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file" + 1
                    + "\";filename=\"" + fileName + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] data = sb.toString().getBytes();
            out.write(data);
            DataInputStream in = new DataInputStream(inputStream);
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
            in.close();
            out.write(end_data);
            out.flush();
            out.close();
            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
 
            String line = null;
            while ((line = reader.readLine()) != null) {
                result+=line;
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("发送POST请求出现异常！" + e);
        }    
        //return new JSONObject(result).toString(1);
        return "";
    }*/
}
