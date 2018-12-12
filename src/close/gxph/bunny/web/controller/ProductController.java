package close.gxph.bunny.web.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.google.common.collect.Maps;

import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.Area;
import close.gxph.bunny.entity.InformationType;
import close.gxph.bunny.entity.LoginUser;
import close.gxph.bunny.entity.Product;
import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.bunny.entity.Dict;
import close.gxph.bunny.entity.SubjectClass;
import close.gxph.bunny.entity.Validate;
import close.gxph.bunny.repository.AreaHessianDao;
import close.gxph.bunny.repository.InformationTypeHessianDao;
import close.gxph.bunny.repository.ProductHessianDao;
import close.gxph.bunny.repository.SubjectClassHessianDao;
import close.gxph.bunny.repository.ValidateHessianDao;
import close.gxph.bunny.service.ApFileEnclosureService;
import close.gxph.bunny.service.DictService;
import close.gxph.bunny.service.InformationTypeService;
import close.gxph.bunny.service.ProductService;
import close.gxph.bunny.service.SubjectService;
import close.gxph.bunny.service.ValidateService;
import close.gxph.bunny.util.HessianUtil;
import close.gxph.bunny.util.UserType;
import close.gxph.core.common.modules.web.Servlets;
import close.gxph.core.constant.Contants;
import close.gxph.core.entity.CoreRole;
/**
 * 产品管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {
	/*@Autowired
	private ProductService productService;*/
	/*@Autowired
	private InformationTypeService informationTypeService;
	@Autowired
	private SubjectService subjectService;*/
	private static ProductHessianDao productService;
	private static InformationTypeHessianDao informationTypeService;
	private static SubjectClassHessianDao subjectService;
	@Autowired
	public ApFileEnclosureService apFileEnclosureService;
	/*@Autowired
	public DictService dictService;*/
	private static AreaHessianDao dictService;
	/*@Autowired
	private ValidateService validateService;*/
	private static ValidateHessianDao validateService;
	
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动(降序)");
		sortTypes.put("asc", "升序");
		productService = (ProductHessianDao) new HessianUtil().getHessianProxyFactory(ProductHessianDao.class, "productService");
		informationTypeService = (InformationTypeHessianDao) new HessianUtil().getHessianProxyFactory(InformationTypeHessianDao.class, "informationTypeService");
		subjectService = (SubjectClassHessianDao) new HessianUtil().getHessianProxyFactory(SubjectClassHessianDao.class, "subjectService");
		dictService = (AreaHessianDao) new HessianUtil().getHessianProxyFactory(AreaHessianDao.class, "dictService");
		validateService = (ValidateHessianDao) new HessianUtil().getHessianProxyFactory(ValidateHessianDao.class, "validateService");
	}
	
	// 打开列表
	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Contants.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "asc") String sortType,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<Product> lists = productService.getProductList(searchParams, pageNumber,
				pageSize, "recordtime",sortType);
		List<InformationType> typelist = informationTypeService.listAllByStatusAndIsbelongtype(Contants.BELONG_STATE_NO);
		if(typelist.size()>0){
			typelist.remove(typelist.size()-1);
		}
		List<SubjectClass> classlist = subjectService.getAllSubjectClassByStatus();
		List<Area> areaList = dictService.getCitys(Contants.SHANXICODE);
		model.addAttribute("informationTypelist", typelist);
		model.addAttribute("subjectClasslist", classlist);
		model.addAttribute("areaList", areaList);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("productList", lists);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "gxph/product/list";
	}
	
	//删除
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		productService.deleteProduct(id);
		redirectAttributes.addFlashAttribute("message", "删除产品成功");
		return "redirect:/product";
	}
	
	/**
	 * 预热申请
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "preheat/{id}")
	public String preheat(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		productService.updatevalidate(id, Contants.CHECK_STATE_ONE);
		redirectAttributes.addFlashAttribute("message", "申请产品预热成功");
		return "redirect:/product";
	}
	
	/**
	 * 上线申请
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "online/{id}")
	public String online(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		productService.updatevalidate(id, Contants.CHECK_STATE_THREE);
		redirectAttributes.addFlashAttribute("message", "申请产品上线成功");
		return "redirect:/product";
	}
	
	/**
	 * 下架申请
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "offline/{id}")
	public String offline(@PathVariable("id") String id,
			RedirectAttributes redirectAttributes) {
		productService.updatevalidate(id, Contants.CHECK_STATE_FIVE);
		redirectAttributes.addFlashAttribute("message", "申请产品下架成功");
		return "redirect:/product";
	}

	//跳转到添加
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(ModelMap model) {
		model.addAttribute("action", "create");
		List<InformationType> typelist = informationTypeService.listAllByStatusAndIsbelongtype(Contants.BELONG_STATE_NO);
		if(typelist.size()>0){
			typelist.remove(typelist.size()-1);
		}
		List<SubjectClass> classlist = subjectService.getAllSubjectClassByStatus();
		List<Area> areaList = dictService.getCitys(Contants.SHANXICODE);
		model.addAttribute("informationTypelist", typelist);
		model.addAttribute("subjectClasslist", classlist);
		model.addAttribute("areaList", areaList);
		return "gxph/product/form";
	}

	//添加
	@RequestMapping(method = RequestMethod.POST, value = "create")
	public String create(@RequestParam(value = "buytimes") String buytimes,HttpServletRequest request,HttpServletResponse response,Product newProduct, RedirectAttributes redirectAttributes) {
		try {
			buytimes = buytimes+" 23:59:59";
			Timestamp ts = Timestamp.valueOf(buytimes); 
			newProduct.setBuytime(ts);
			LoginUser lu = new UserType().getUserType();
			newProduct.setRecorduserid(lu.getRecorduserid());
			newProduct.setRecordusername(lu.getRecordusername());
			newProduct.setRecordtime(lu.getRecordtime());
			String adid = productService.addProduct(newProduct);
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,adid,Contants.OBJ_TYPE_AD,new ArrayList<String>());
			if(b){
				redirectAttributes.addFlashAttribute("message", "产品添加成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "产品添加失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "产品添加失败");
			e.printStackTrace();
		}
		return "redirect:/product";
	}

	//跳转到修改
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("action", "update");
		Product Product=productService.getProduct(id);
		List<ApFileEnclosure> list = apFileEnclosureService.findByObjId(id);
		if(null!=list&&list.size()>0){
			Product.setApFileEnclosureList(list);
		}
		List<InformationType> typelist = informationTypeService.listAllByStatusAndIsbelongtype(Contants.BELONG_STATE_NO);
		if(typelist.size()>0){
			typelist.remove(typelist.size()-1);
		}
		List<SubjectClass> classlist = subjectService.getAllSubjectClassByStatus();
		List<Area> areaList = dictService.getCitys(Contants.SHANXICODE);
		model.addAttribute("informationTypelist", typelist);
		model.addAttribute("subjectClasslist", classlist);
		model.addAttribute("areaList", areaList);
		model.addAttribute("product",Product);
		return "gxph/product/form";
	}
	
	/**
	 * 跳转到审核查看图片
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "findpicupload/{id}")
	public String findpicupload(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("id", id);
		List<ApFileEnclosure> list = apFileEnclosureService.findByObjId(id);
		String listjson = JSONArray.fromObject(list).toString();
		model.addAttribute("filelist",list);
		model.addAttribute("apFileList",listjson);
		model.addAttribute("listsize",list.size());
		return "gxph/product/findpicupload";
	}
	
	/**
	 * 产品跳转到添加图片
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "picupload/{id}")
	public String picupload(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("id", id);
		List<ApFileEnclosure> list = apFileEnclosureService.findByObjId(id);
		String listjson = JSONArray.fromObject(list).toString();
		model.addAttribute("filelist",list);
		model.addAttribute("apFileList",listjson);
		model.addAttribute("listsize",list.size());
		return "gxph/product/picupload";
	}

	//修改
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public String update(@RequestParam(value = "buytimes") String buytimes,HttpServletRequest request,HttpServletResponse response,Product product, RedirectAttributes redirectAttributes) {
		try {
			buytimes = buytimes+" 23:59:59";
			Timestamp ts = Timestamp.valueOf(buytimes); 
			product.setBuytime(ts);
			LoginUser lu = new UserType().getUserType();
			product.setRecorduserid(lu.getRecorduserid());
			product.setRecordusername(lu.getRecordusername());
			product.setRecordtime(lu.getRecordtime());
			String adid = productService.alterProduct(product);
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,adid,Contants.OBJ_TYPE_AD,new ArrayList<String>());
			if(b){
				redirectAttributes.addFlashAttribute("message", "产品编辑成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "产品编辑失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "产品编辑失败");
			e.printStackTrace();
		}
		return "redirect:/product";
	}
	
	/**
	 * 跳转到产品审核
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "productvalidat")
	public String productvalidat(
		@RequestParam(value = "page", defaultValue = "1") int pageNumber,
		@RequestParam(value = "page.size", defaultValue = Contants.PAGE_SIZE) int pageSize,
		@RequestParam(value = "sortType", defaultValue = "asc") String sortType,
		Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Session session = SecurityUtils.getSubject().getSession();
		CoreRole rolecode = (CoreRole) session.getAttribute(Contants.ROLE_CODE);
		String code =  rolecode.getCode();
		if(!code.equals(Contants.USER_TYPE_ADMIN)){
			if(code.equals(Contants.USER_TYPE_START)){
				code = Contants.CHECK_VALIDATE_NO;
			}else if(code.equals(Contants.USER_TYPE_REPEAT)){
				code = Contants.CHECK_VALIDATE_ONE;
			}
			searchParams.put("EQ_issingular", code);
		}else{
			/*List<String> codelist = new ArrayList<String>();
			codelist.add(Contants.CHECK_VALIDATE_NO);
			codelist.add(Contants.CHECK_VALIDATE_ONE);
			searchParams.put("IN_issingular", codelist);*/
		}
		Page<Product> lists = productService.getProductList(searchParams, pageNumber,
				pageSize, "recordtime",sortType);
		List<InformationType> typelist = informationTypeService.listAllByStatusAndIsbelongtype(Contants.BELONG_STATE_NO);
		if(typelist.size()>0){
			typelist.remove(typelist.size()-1);
		}
		List<SubjectClass> classlist = subjectService.getAllSubjectClassByStatus();
		List<Area> areaList = dictService.getCitys(Contants.SHANXICODE);
		model.addAttribute("informationTypelist", typelist);
		model.addAttribute("subjectClasslist", classlist);
		model.addAttribute("areaList", areaList);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("productList", lists);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "gxph/product/validatelist";
	}
	
	/**
	 * 跳转到审核页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "validateform/{id}")
	public String validateform(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("action", "update");
		Product Product=productService.getProduct(id);
		List<ApFileEnclosure> list = apFileEnclosureService.findByObjId(id);
		if(null!=list&&list.size()>0){
			Product.setApFileEnclosureList(list);
		}
		List<InformationType> typelist = informationTypeService.listAllByStatusAndIsbelongtype(Contants.BELONG_STATE_NO);
		if(typelist.size()>0){
			typelist.remove(typelist.size()-1);
		}
		List<SubjectClass> classlist = subjectService.getAllSubjectClassByStatus();
		List<Area> areaList = dictService.getCitys(Contants.SHANXICODE);
		model.addAttribute("informationTypelist", typelist);
		model.addAttribute("subjectClasslist", classlist);
		model.addAttribute("areaList", areaList);
		model.addAttribute("product",Product);
		return "gxph/product/validateform";
	}
	
	/**
	 * 产品审核
	 * @param id
	 * @param model
	 * @param mark
	 * @param reasons
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "validatesubmits/{id}")
	public String validatesubmits(@PathVariable("id") String id, ModelMap model,@RequestParam(value = "mark") String mark,@RequestParam(value = "reasons") String reasons,@RequestParam(value = "servicepropercent") int servicepropercent,@RequestParam(value = "returnpropercent") int returnpropercent) {
		Product product=productService.getProduct(id);
		product.setServicepropercent(servicepropercent);
		product.setReturnpropercent(returnpropercent);
		String status = product.getStatus();
		Validate vd = new Validate();
		LoginUser lu = new UserType().getUserType();
		vd.setRecordtime(lu.getRecordtime());
		vd.setRecorduserid(lu.getRecorduserid());
		vd.setRecordusername(lu.getRecordusername());
		vd.setReason("");
		vd.setProductid(id);
		if(mark.equals("0")){
			/*if(status.equals(Contants.CHECK_STATE_NO)){
				product.setStatus(Contants.CHECK_STATE_ONE);
				product.setIssingular(Contants.CHECK_VALIDATE_ONE);
				vd.setState(0);
			}else*/ 
			if(status.equals(Contants.CHECK_STATE_ONE)){
				if(product.getIssingular().equals(Contants.CHECK_VALIDATE_NO)){
					product.setIssingular(Contants.CHECK_VALIDATE_ONE);
					vd.setState(0);
				}else if(product.getIssingular().equals(Contants.CHECK_VALIDATE_ONE)){
					product.setStatus(Contants.CHECK_STATE_TWO);
					product.setIssingular(Contants.CHECK_VALIDATE_TWO);
					vd.setState(1);
				}
			}else if(status.equals(Contants.CHECK_STATE_THREE)){
				if(product.getIssingular().equals(Contants.CHECK_VALIDATE_NO)){
					product.setIssingular(Contants.CHECK_VALIDATE_ONE);
					vd.setState(2);
				}else if(product.getIssingular().equals(Contants.CHECK_VALIDATE_ONE)){
					product.setStatus(Contants.CHECK_STATE_FOUR);
					product.setIssingular(Contants.CHECK_VALIDATE_TWO);
					vd.setState(3);
				}
			}else if(status.equals(Contants.CHECK_STATE_FIVE)){
				if(product.getIssingular().equals(Contants.CHECK_VALIDATE_NO)){
					product.setIssingular(Contants.CHECK_VALIDATE_ONE);
					vd.setState(4);
				}else if(product.getIssingular().equals(Contants.CHECK_VALIDATE_ONE)){
					product.setStatus(Contants.CHECK_STATE_SIX);
					product.setIssingular(Contants.CHECK_VALIDATE_TWO);
					vd.setState(5);
				}
			}
		}else{
			product.setStatus(Contants.CHECK_STATE_SEVEN);
			product.setIssingular(Contants.CHECK_VALIDATE_TWO);
			vd.setReason(reasons);
			vd.setState(6);
		}
		productService.validate(product,vd);
		return "redirect:/product/productvalidat";
	}
	
	/**
	 * 跳转到审核流程页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "validatelist/{id}")
	public String validatelist(@PathVariable("id") String id, ModelMap model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType) {
		Map<String, Object> searchParams = new TreeMap<String,Object>();
		searchParams.put("EQ_productid", id);
		Page<Validate> lists = validateService.getValidates(searchParams, pageNumber,
				Contants.PAGE_SIZES, "recordtime",sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("sortType", sortType);
		model.addAttribute("validateList", lists);
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "gxph/product/validateprocesslist";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "uploadpic/{id}")
	public String uploadpic(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "picid") String picid,@RequestParam(value = "picstate") String picstate,
			@PathVariable("id") String id,RedirectAttributes redirectAttributes) {
		try {
			List<String> list = new ArrayList<String>();
			
			String[] statestr = picstate.split(",");
			String[] picidstr = picid.split(",");
			for (int i = 0; i < statestr.length; i++) {
				String state = statestr[i];
				if(state.equals("true")){
					if(picidstr.length==0||picidstr[i].equals("DEFAULT_VALUES")){
						list.add("");
					}else{
						list.add(picidstr[i]);
					}
				}
			}
			System.out.println(list);
			boolean b = apFileEnclosureService.addFileEnclosure(request, response,id,Contants.OBJ_TYPE_PRODUCT,list);
			if(b){
				redirectAttributes.addFlashAttribute("message", "图片上传成功");
			}else{
				redirectAttributes.addFlashAttribute("fail", "图片上传失败");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "图片上传失败");
			e.printStackTrace();
		}
		return "redirect:/product";
	}
}
