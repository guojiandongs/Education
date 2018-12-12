package close.gxph.core.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.core.entity.CoreRole;

public interface CoreRoleHessianDao{
	CoreRole findOne(String id);
	void delete(String id);
	Page<CoreRole> getCoreRole(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy, String order);
	CoreRole saveRole(CoreRole role);
}
