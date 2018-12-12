package close.gxph.core.common.util;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import close.gxph.core.common.modules.persistence.DynamicSpecifications;
import close.gxph.core.common.modules.persistence.SearchFilter;

public class CommonService<T> {

	@SuppressWarnings("rawtypes")
	Class entityClass = GenericSuperClass.getGenericSuperClass(this.getClass());

	/**
	 * 创建动态查询条件组合.
	 */
	@SuppressWarnings("unchecked")
	protected Specification<T> buildSpecification(
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), entityClass);
		return spec;
	}

	/**
	 * 创建动态查询条件组合.
	 * pageNumber:页码
	 * pageSize：每页数据数量
	 * sortBy：排序字段
	 * orderType：排序方式
	 */
	// 原来的
	// protected PageRequest buildPageRequest(int pageNumber, int pageSize,
	// String sortBy, String order) {
	// Sort sort = null;
	// if (StringX.equals("desc", order))
	// sort = new Sort(Direction.DESC, sortBy);
	// else if (StringX.equals("asc", order))
	// sort = new Sort(Direction.ASC, sortBy);
	// return new PageRequest(pageNumber - 1, pageSize, sort);
	// }
	// 改过的
	protected PageRequest buildPageRequest(int pageNumber,int pageSize,
			String sortBy,String orderType){
		Sort sort = null ;
		if("auto".equals(orderType)){
			sort = new Sort(Direction.DESC,sortBy);
		}else if("asc".equals(orderType)) {
			sort = new Sort(Direction.ASC,sortBy);
		}
		return new PageRequest(pageNumber - 1,pageSize,sort);
	}
}
