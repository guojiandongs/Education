package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.Area;
import close.gxph.bunny.entity.Dict;

public interface AreaHessianDao{
	List<Area> getCitys(String province);
	Page<Dict> getDicts(Map<String,Object> searchParams,
			int pageNumber, int pageSize, String sortBy);
	void addDict(Dict dict);
	void deleteDict(String id);
	Dict getDict(String id);
	void alterDict(Dict dict);
}
