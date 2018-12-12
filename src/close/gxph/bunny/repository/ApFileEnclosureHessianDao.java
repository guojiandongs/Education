package close.gxph.bunny.repository;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import close.gxph.bunny.entity.ApFileEnclosure;

public interface ApFileEnclosureHessianDao{
	
	boolean addFileEnclosure(HttpServletRequest request,HttpServletResponse response,String id,String objtype,List<String> list);

	List<ApFileEnclosure> findByObjId(String ObjId);
}
