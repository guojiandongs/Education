package close.gxph.bunny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import close.gxph.bunny.entity.AdPic;


public interface AdPicDao extends JpaRepository<AdPic, String>,
		JpaSpecificationExecutor<AdPic> {
	@Modifying
	@Query("from AdPic a where a.seq=?")
	AdPic findAdBySeq(Integer seq);
	
	@Modifying
	@Query("select a from AdPic a  where a.state='1' order by a.seq asc")
	List<AdPic> findAdBySeqAll();
}
