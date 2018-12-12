package close.gxph.core.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.core.entity.CoreRes;
import close.gxph.core.entity.CoreRole;

public interface CoreRoleResHessianDao{
	List<CoreRes> list(String roleid);
	void save(String roleid,String resourceids);
}
