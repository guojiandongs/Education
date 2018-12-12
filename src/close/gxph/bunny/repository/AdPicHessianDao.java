package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import close.gxph.bunny.entity.AdPic;
import close.gxph.bunny.entity.ApFileEnclosure;

public interface AdPicHessianDao{
	List<AdPic> findAdBySeq(Integer seq);
	
	List<AdPic> findAdBySeqAll();
	
	Page<AdPic> getAdPics(Map<String, Object> searchParams, int pageNumber,
			int pageSize,String sortBy,String sortType);
	
	void deleteAdPic(String id);
		
	String addAdPic(AdPic adPic);
	
	AdPic getAdPic(String id);
	
	String alterAdPic(AdPic adPic);
	
	AdPic getAdBySeq(Integer seq);
}
