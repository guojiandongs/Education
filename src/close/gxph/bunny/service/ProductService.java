package close.gxph.bunny.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.bunny.entity.LoginUser;
import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Product;
import close.gxph.bunny.entity.Usinesses;
import close.gxph.bunny.entity.Validate;
import close.gxph.bunny.repository.ProductDao;
import close.gxph.bunny.repository.ValidateDao;
import close.gxph.bunny.util.UserType;
import close.gxph.core.common.util.CommonService;
import close.gxph.core.constant.Contants;
@Component
@Transactional
public class ProductService extends CommonService<Product>{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ValidateDao validateDao;
	
	public String addProduct(Product product) {
		LoginUser lu = new UserType().getUserType();
		product.setRecorduserid(lu.getRecorduserid());
		product.setRecordusername(lu.getRecordusername());
		product.setRecordtime(lu.getRecordtime());
		product.setStatus(Contants.CHECK_STATE_NO);
		product.setIssingular(Contants.CHECK_VALIDATE_NO);
		Product products = productDao.save(product);
		return products.getId();
	}

	/**
	 * 编辑信息.
	 */
	public String alterProduct(Product product) {
		String id = product.getId();
		Product us = productDao.getOne(id);
		if(null!=us){
			product.setStatus(us.getStatus());
			product.setIssingular(us.getIssingular());
			product.setRecordtime(us.getRecordtime());
			product.setRecorduserid(us.getRecorduserid());
			product.setRecordusername(us.getRecordusername());
		}
		Product pd = productDao.save(product);
		return pd.getId();
	}
	
	/**
	 * 获取信息.
	 */
	public Product getProduct(String id) {
		return productDao.findOne(id);
	}

	public List<Product> listAll() {
		return productDao.findAll();
	}
	
	/**
	 * 根据条件返回信息列表.
	 */
	public Page<Product> getProductList(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy,orderType);
		Specification<Product> spec = buildSpecification(searchParams);
		return productDao.findAll(spec, pageRequest);
	}

	public void deleteProduct(String id) {
		productDao.delete(id);
	}
	
	/**
	 * 审核
	 */
	public void validate(Product product,Validate validate) {
		Product pd = productDao.save(product);
		validateDao.save(validate);
	}
	
	/**
	 * 审核
	 */
	public void updatevalidate(String id,String status) {
		Product product = productDao.findOne(id);
		product.setStatus(status);
		product.setIssingular(Contants.CHECK_VALIDATE_NO);
		productDao.save(product);
	}
}
