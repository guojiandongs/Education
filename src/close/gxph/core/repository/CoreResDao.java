package close.gxph.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.ApFileEnclosure;
import close.gxph.core.entity.CoreRes;

public interface CoreResDao extends JpaSpecificationExecutor<CoreRes>,JpaRepository<CoreRes, String>{
	/*
	 * 根据文件的对象id，找到所有的文件记录
	 */
	public List<CoreRes> findAllByParentid(String parentid);
}
