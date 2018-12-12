package close.gxph.bunny.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import close.gxph.core.constant.Contants;

public class FileOperateUtil {

	 public static String FILEDIR = "D:/SxlyUploadFiles/";
	    /**
	     * 上传
	     * @param request
	     * @param objType 从哪个模块上传的文件 设立申请或其他
	     * @throws IOException
	     * return 
	     * 			map(objType:从哪上传的,
	     * 				fileType:什么类型文件,
	     * 				fileOriginName:文件原来名称,
	     * 				fileNewName:文件存放在服务器的名称,
	     * 				fileAbsolutePath；文件存放在服务器的绝对路径,
	     * 				fileRelativePath；文件存放在服务器的相对路径)
	     */
	    public static Map<String,String> apupload(MultipartHttpServletRequest multiRequest,String objType) throws IOException{       
	       // MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
	        Map<String, MultipartFile> fileMap = multiRequest.getFileMap();      
	        File file = new File(FILEDIR);
	        if (!file.exists()) {
 	        	file.mkdir();
 	        }
	        Map<String,String> map = new HashMap<String, String>();
	        Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
	        while(it.hasNext()){
	            Map.Entry<String, MultipartFile> entry = it.next();
	            MultipartFile mFile = entry.getValue();
	            if(mFile.getSize() != 0 && !"".equals(mFile.getName())){
//	            	map = initFilePath(mFile.getOriginalFilename(),objType,relativePath);
	            	map = initFilePath(mFile.getOriginalFilename(),objType);
	                write(mFile.getInputStream(), new FileOutputStream(map.get("fileAbsolutePath")));
	                map.put("result", "success");
	            }
	        }
	        return map;
	    }
	    public static Map<String,String> upload(HttpServletRequest request,String objType) throws IOException{       
	        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
	        Map<String, MultipartFile> fileMap = mRequest.getFileMap();      
	        File file = new File(FILEDIR);
	        if (!file.exists()) {
 	        	file.mkdir();
 	        }
	        Map<String,String> map = new HashMap<String, String>();
	        Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
	        while(it.hasNext()){
	            Map.Entry<String, MultipartFile> entry = it.next();
	            MultipartFile mFile = entry.getValue();
	            if(mFile.getSize() != 0 && !"".equals(mFile.getName())){
//	            	map = initFilePath(mFile.getOriginalFilename(),objType,relativePath);
	            	map = initFilePath(mFile.getOriginalFilename(),objType);
	                write(mFile.getInputStream(), new FileOutputStream(map.get("fileAbsolutePath")));
	            }
	        }
	        return map;
	    }
	    private static Map<String,String> initFilePath(String name,String objType) {
	        String suffix = name.substring(name.lastIndexOf(".") + 1);
	        String fname = name.substring(0,name.lastIndexOf("."));
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String lastFolder = sdf.format(date);
			String fileName = sdf1.format(date) + ((int)(Math.random() *900)+100);
			String path = "";
			String rpath = "";
			if(objType.equals(Contants.OBJ_TYPE_AD)){	//旅行社上传附件
//				path = relativePath + "uploadFiles\\" + fname.split("]")[1] + "\\" + lastFolder + "\\" + fileName + "." +suffix;
				path = FILEDIR + fname.split("]")[1]  + "/" + fileName + "." +suffix;
				rpath = "uploadFiles/" + fname.split("]")[1] + "/" + fileName + "." +suffix;
			}else{
//				path = relativePath + "uploadFiles\\" + objType + "\\" + lastFolder + "\\" + fileName + "." +suffix;
				path = FILEDIR + objType +  "/" + fileName + "." +suffix;
				rpath = "uploadFiles/" + objType + "/" + fileName + "." +suffix;
			}
	        String [] arr = path.split("/");
	        String tempPath = "";
	        for (int i=0; i < arr.length; i++) {
	        	if(i != arr.length - 1){
	        		tempPath = tempPath.trim() + arr[i] + "/";
		        	File file = new File(tempPath);
		 	        if (!file.exists()) {
		 	        	file.mkdir();
		 	        }
	        	}
			}
//	        System.out.println("创建文件 ； " + flag);
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("objType", objType);
	        map.put("fileType", suffix);
	        map.put("fileOriginName", fname+"."+suffix);
	        map.put("fileNewName", fileName);
	        map.put("fileAbsolutePath", path);
	        map.put("fileRelativePath", rpath);
	        return map;
	    }
	   
	    /**
	     * 下载
	     * @param request
	     * @param filePath文件路径
	     * @throws IOException
	     */
	    public static void download(String filePath, ServletOutputStream out) {
	        try {
	            FileInputStream in = new FileInputStream(new File(filePath));
	            write(in, out);
	        } catch (FileNotFoundException e) {
	            try {
	                FileInputStream in = new FileInputStream(new File(new String(filePath.getBytes("iso-8859-1"),"utf-8")));
	                write(in, out);
	            } catch (IOException e1) {              
	                e1.printStackTrace();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }       
	    }
	    /**
	     * 写入数据
	     * @param in
	     * @param out
	     * @throws IOException
	     */
	    public static void write(InputStream in, OutputStream out) throws IOException{
	        try{
	            byte[] buffer = new byte[1024];
	            int bytesRead = -1;
	            while ((bytesRead = in.read(buffer)) != -1) {
	                out.write(buffer, 0, bytesRead);
	            }
	            out.flush();
	        } finally {
	            try {
	                in.close();
	            }
	            catch (IOException ex) {
	            }
	            try {
	                out.close();
	            }
	            catch (IOException ex) {
	            }
	        }
	    }  

	    public static void uploads(HttpServletRequest request){
	    	String fileDir = "D:/SxlyUploadFiles/";
	    	String localFileName = "";
	    	String serverFileName = "";
	    	String serverFilePath = "";
	    	if(ServletFileUpload.isMultipartContent(request)) {
	    	    DiskFileItemFactory factory = new DiskFileItemFactory(); // 基于磁盘文件项目创建一个工厂对象
	    	    //factory.setSizeThreshold(20*1024);
	    	    factory.setRepository(factory.getRepository());
	    	    ServletFileUpload upload = new ServletFileUpload(factory); // 创建一个新的文件上传对象
	    	    int size = 10*1024*1024;  // 最大上传文件，不超过10M
	    	    List formlists = null;
	    	    FileItem formitem;
	    	       try{
	    	           formlists = upload.parseRequest(request); // 解析上传请求
	    	       }catch(FileUploadException e){
	    	              e.printStackTrace();
	    	       }   
	    	       Iterator iter = formlists.iterator(); // 枚举方法
	    	       while(iter.hasNext()) {
	    	           formitem = (FileItem)iter.next(); // 获取FileItem对象
	    	           if(!formitem.isFormField()) {     // 判断是否为文件域
	    	              if(formitem.getName()!=null && !formitem.getName().equals("")) { // 判断是否选择了文件
	    	                  long upFileSize = formitem.getSize(); // 上传文件大小
	    	                  if (upFileSize > size) {
	    	                     System.out.println("<script>alert('上传文件太大！[<100M]')</script>");
	    	                     return;
	    	                  }   
	    	                  localFileName = formitem.getName();   // 获取文件名
	    	                  int ii = localFileName.lastIndexOf(".");
	    	                  String sExt = localFileName.substring(ii,localFileName.length());//取文件名的后缀
	    	                  if ((!sExt.equals(".xls")) && (!sExt.equals(".xlsx"))) {
	    	                	  System.out.println("<script>alert('请选择excel文件！')</script>");
	    	                     return;
	    	                  }
	    	                  //得到不重复的文件名，这一步是为了防止同时上传两个同文件名的excel而做的，避免文件名重复   
	    	                  Date dt = new Date(System.currentTimeMillis());   
	    	                  SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    	                  serverFileName = fmt.format(dt);    
	    	                  serverFileName = serverFileName + sExt;   
	    	                  //如果不存在该目录，则新建一个   
	    	                  File dir =new File(fileDir);   
	    	                  if (!dir.exists()) {   
	    	                     dir.mkdirs();   
	    	                  }
	    	                  serverFilePath = fileDir+"/"+serverFileName;
	    	                  File serverFile = new File(serverFilePath);
	    	                  try {
	    	                     formitem.write(serverFile);
	    	                  }catch(Exception e) {
	    	                     e.printStackTrace();
	    	                  } 
	    	              }else {                     
	    	            	  System.out.println("<script>alert('没有选择文件！')</script>");
	    	                  return;
	    	              }   
	    	           }else {
	    	              try {
	    	                  if("column".equals(formitem.getFieldName())) {
	    	                	  Integer column = Integer.parseInt(formitem.getString("gb2312").trim());
	    	                         //System.out.println(column);
	    	                  }
	    	              }catch (NumberFormatException e) {
	    	                  e.printStackTrace();
	    	                  System.out.println(3);
	    	                  return;
	    	              } catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println(2);
						}
	    	           }
	    	       }
	    	}
	    	}

}
