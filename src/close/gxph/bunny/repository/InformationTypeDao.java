package close.gxph.bunny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.InformationType;

public interface InformationTypeDao extends JpaRepository<InformationType, String>,
		JpaSpecificationExecutor<InformationType> {
	
	List<InformationType> findByStatus(String status);
	
	List<InformationType> findByStatusAndIsbelongtype(String status,String isbelongtype);
}
