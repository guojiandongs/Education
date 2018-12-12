package close.gxph.bunny.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import close.gxph.bunny.entity.Area;
import close.gxph.bunny.entity.Dict;
import close.gxph.bunny.repository.AreaDao;
import close.gxph.bunny.repository.DictDao;
import close.gxph.core.common.modules.persistence.DynamicSpecifications;
import close.gxph.core.common.modules.persistence.SearchFilter;
import close.gxph.core.common.util.StringX;
@Component
@Transactional
public class DictService {
	@Autowired
	private DictDao dictDao;
	@Autowired
	private AreaDao areaDao;
	
	public List<Dict> getDicts(String keyword) {
		return dictDao.findAllByWord(keyword);
	}
	/**
	 * 创建字典信息
	 */
	public void addDict(Dict dict){
		dictDao.save(dict);
	}
	/**
	 * 编辑字典信息
	 */
	public void alterDict(Dict dict){
		dictDao.save(dict);
	}
	/**
	 * 查找一条字典信息
	 */
	public Dict getDict(String id){
		return dictDao.findOne(id);
	}
	/**
	 * 查找所有的字典信息
	 */
	public List<Dict> findAll(){
		return dictDao.findAll();
	}
	/**根据条件查询*/
	public List<Dict> findAll(Map<String,Object> searchParams){
		Specification<Dict> spec = buildSpecification(searchParams);
		return dictDao.findAll(spec);
	}
	
	/**
	 * 删除字典信息
	 */
	public void deleteDict(String id){
		dictDao.delete(id);
	}
	/**
	 * 根据条件返回信息列表
	 */
	public Page<Dict> getDicts(Map<String,Object> searchParams,
			int pageNumber, int pageSize, String sortBy){
		PageRequest pageRequest = buildPageRequest(pageNumber,pageSize,sortBy);
		Specification<Dict> spec = buildSpecification(searchParams);
	    return dictDao.findAll(spec, pageRequest);
	}
	private PageRequest buildPageRequest(int pageNumber,int pageSize,String sortType){
		Sort sort = null ;
		if("auto".equals(sortType)){
			sort = new Sort(Direction.DESC,"id");
		} else if("asc".equals(sortType)){
			sort = new Sort(Direction.ASC,"id");
		}
		return new PageRequest(pageNumber - 1,pageSize,sort);
	}
	/**
	 * 创建动态查询条件组合
	 */
	private Specification<Dict> buildSpecification(Map<String,Object> searchParams){
		Map<String,SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Dict> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), Dict.class);
		return spec ;
	}

	public List<Area> getAreas() {
		return areaDao.findAll();
	}

	public List<Area> getProvinces() {
		List<Area> provinces = new ArrayList<Area>();
		List<Area> areas = areaDao.findAll();
		for (int i = 0; i < areas.size(); i++) {
			String tempCode = areas.get(i).getCode();
			if (StringX.equals(tempCode.substring(2), "0000"))
				provinces.add(areas.get(i));
		}
		return provinces;
	}
    //获取所有市的列表
	public List<Area> getAllCitys(){
		List<Area> citys = new ArrayList<Area>();
		List<Area> areas = areaDao.findAll();
		for(int i= 0;i<areas.size();i++){
			String tempCode = areas.get(i).getCode();
			if(StringX.equals(tempCode.substring(4), "00"))
				citys.add(areas.get(i));
		}
		return citys ;
	}
	
	/**
	 * 获取省下所有市
	 * @param province
	 * @return
	 */
	public List<Area> getCitys(String province) {
		List<Area> citys = new ArrayList<Area>();
		List<Area> areas = areaDao.findAll();
		for (int i = 0; i < areas.size(); i++) {
			String tempCode = areas.get(i).getCode();
			if (!StringX.equals(tempCode, province)
					&& StringX.equals(tempCode.substring(0, 2),
							province.substring(0, 2))
					&& StringX.equals(tempCode.substring(4), "00")) {
				citys.add(areas.get(i));
			}
		}
		return citys;
	}

	public List<Area> getCountys(String city) {
		List<Area> countys = new ArrayList<Area>();
		List<Area> areas = areaDao.findAll();
		for (int i = 0; i < areas.size(); i++) {
			String tempCode = areas.get(i).getCode();
			if (!StringX.equals(tempCode, city)
					&& StringX.equals(tempCode.substring(0, 4),
							city.substring(0, 4))) {
				countys.add(areas.get(i));
			}
		}
		return countys;
	}
}
