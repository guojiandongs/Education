package close.gxph.bunny.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.bunny.repository.ApFileEnclosureDao;
import close.gxph.bunny.repository.ApFileEnclosureHessianDao;
import close.gxph.bunny.util.FileOperateUtil;
import close.gxph.bunny.util.FileOperateUtilNew;
import close.gxph.bunny.util.StrUtil;
import close.gxph.core.common.util.CommonService;
import close.gxph.core.constant.Contants;

@Component
@Transactional
public class ApFileEnclosureService extends CommonService<ApFileEnclosure> implements ApFileEnclosureHessianDao{
	@Autowired
	private ApFileEnclosureDao apFileEnclosureDao;
	
	/**
	 * 添加文件附件信息
	 */
	public ApFileEnclosure addApFileEnclosure(HttpServletRequest request) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		
		map = FileOperateUtil.upload(request,"NEWS");
		String filename = map.get("fileOriginName");
		//ApFileEnclosure fileEn = new ApFileEnclosure();
		ApFileEnclosure fileEnclosure = new ApFileEnclosure();
				//删除文件历史记录
				fileEnclosure.setFileName(filename);
				String fileType = map.get("fileType");
				fileEnclosure.setFileType(Contants.FILE_TYPE_TEXT);
				
				fileEnclosure.setFileAlias(map.get("fileNewName"));
				fileEnclosure.setPathRel(map.get("fileRelativePath"));
				fileEnclosure.setPathAbs(map.get("fileAbsolutePath"));
				fileEnclosure.setSaveTime(new Timestamp(System.currentTimeMillis()));
				fileEnclosure.setDeleteState(Contants.DEL_STATE_NO);
				
		return fileEnclosure;
	}
	/**
	 * 编辑文件附件的基本信息
	 */
	public ApFileEnclosure updToaFileEnclosure(ApFileEnclosure toaFileEnclosure){
		return apFileEnclosureDao.save(toaFileEnclosure);
	}
	/**
	 * 删除文件附件的基本信息
	 */
	public ApFileEnclosure delFileEnclosure(ApFileEnclosure apFileEnclosure){
		apFileEnclosure.setDeleteState(Contants.DEL_STATE_YES);
		return apFileEnclosureDao.save(apFileEnclosure);
	}
	/**
	 * 根据id删除文件
	 */
	public void delFileEnclosureByID(String id){
		apFileEnclosureDao.delete(id);
	}
	/**
	 * 根据对象id找到所有的文件
	 */
	public List<ApFileEnclosure> findByObjId(String ObjId){
		return apFileEnclosureDao.findByObjIdAndDeleteState(ObjId,"0");
	}
	
	/**
	 * 查找一条文件附件的基本信息，根据id
	 */
	public ApFileEnclosure findToaFileEnclosure(String id){
		return apFileEnclosureDao.findOne(id);
	}
	
	/**
	 * 根据条件返回文件附件信息列表
	 */
	public Page<ApFileEnclosure> findToaFileEnclosures(Map<String,Object> searchParams,
			int pageNumber, int pageSize,String sortBy,String order){
		PageRequest pageRequest = buildPageRequest(pageNumber,pageSize,sortBy,order);
		Specification<ApFileEnclosure> spec = buildSpecification(searchParams);
		return apFileEnclosureDao.findAll(spec,pageRequest);
	}
	/**
	 * 根据条件返回文件附件信息列表
	 */
	public List<ApFileEnclosure> findToaFileEnclosureList(Map<String,Object> searchParams){
		Specification<ApFileEnclosure> spec = buildSpecification(searchParams);
		return apFileEnclosureDao.findAll(spec);
	}
	
	/**
	 * 添加文件时，根据objType,objId,fileType删除该文件对应的历史记录
	 */
	public boolean delFilesByParams(String objType,String objId,String propertyType){
		List<ApFileEnclosure> files = apFileEnclosureDao.findFilesByParams(objType, objId, propertyType);
		List<ApFileEnclosure> newList = new ArrayList<ApFileEnclosure>();
		for (ApFileEnclosure tf : files) {
			tf.setDeleteState(Contants.DEL_STATE_YES);
			ApFileEnclosure newTf = apFileEnclosureDao.save(tf);
			if(Contants.DEL_STATE_YES.equals(newTf.getDeleteState())){
				newList.add(newTf);
			}
		}
		return files.size() == newList.size();
	}

	
	
	public boolean addFileEnclosure(HttpServletRequest request,HttpServletResponse response,String id,String objtype,List<String> list){
		boolean b = false;
		try {
			Map<String,Map<String,Object>> map = new HashMap<String,Map<String,Object>>();
			 CommonsMultipartResolver resolver = new CommonsMultipartResolver(
		                request.getServletContext());
			 if (resolver.isMultipart(request) && !StrUtil.isEmpty(id)) {
		            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		            // 取得request中的所有文件名
						map = FileOperateUtilNew.trainUpload(multiRequest,objtype,id);
		                //添加文件  
						Map<String,Object> searchParams = new TreeMap<String,Object>();
						searchParams.put("EQ_objId", id);
						
			            //searchParams.put("EQ_id", id);
			             List<ApFileEnclosure>  apFileEnclosures = findToaFileEnclosureList(searchParams);
		                if(!StrUtil.isEmpty(map) && map.size()>0){
		                	for (int i = 1 ; i <= map.size(); i ++ ) {
		                		 Map<String,Object> mapIn = map.get("message"+i);
		                		 if(mapIn.get("result") != null && mapIn.get("result").equals("success")){
		                			 if(!objtype.equals(Contants.OBJ_TYPE_PRODUCT)){
					                	 if(null!=apFileEnclosures && apFileEnclosures.size()>0 ){
					                      		//如果有文件传来，且数据库已经保存该对象的文件，那么先删除数据库内容，再进行添加
					                      		for (ApFileEnclosure apFileEnclosure : apFileEnclosures) {
					                      			delFileEnclosure(apFileEnclosure);
					         					}
					                      }
		                			 }
			 					 }
		                		 ApFileEnclosure  apFileEnclosure = (ApFileEnclosure)mapIn.get("apFileEnclosure");
		                		 if(objtype.equals(Contants.OBJ_TYPE_PRODUCT)){
		                			 String picid = list.get(i-1);
		                			 if(!picid.equals("")){
		                				 apFileEnclosure.setId(picid);
		                			 }
		                		 }
		                		 updToaFileEnclosure(apFileEnclosure);
		                	}
		                }
		               b= true;
		        }else{
		        	b= false;
		        }
		 	}catch (Exception e) {
		 		e.printStackTrace();
		 		b= false;
			}
		return b;
	}
}
