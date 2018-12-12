package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.Information;
import close.gxph.bunny.entity.InformationType;

public interface InformationHessianDao{
	Page<Information> getInformationPics(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String orderType);
	void deleteInformation(String id);
	String alterInformation(Information information);
	Information getInformation(String id);
}
