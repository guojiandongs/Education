package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import close.gxph.bunny.entity.InformationType;

public interface InformationTypeHessianDao{
	
	Page<InformationType> getInformationPics(Map<String, Object> searchParams, int pageNumber,
			int pageSize,String sortBy,String sortType);
	void deleteInformation(String id);
	String alterInformation(InformationType information);
	InformationType getInformation(String id);
	List<InformationType> listAllByStatusAndIsbelongtype(String status);
}
