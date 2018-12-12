package close.gxph.bunny.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.User;
import close.gxph.bunny.entity.Usinesses;

public interface UsinessesHessianDao{
	Page<Usinesses> getAdPics(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType);
	void deleteAdPic(String id);
	void addUsinesses(Usinesses usinesses);
	Usinesses getUsinesses(String id);
	void alterUsinesses(Usinesses usinesses);
	Usinesses checkname(String username);
}
