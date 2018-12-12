package close.gxph.bunny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import close.gxph.bunny.entity.ApFileEnclosure;

public interface ApFileEnclosureDao extends JpaRepository<ApFileEnclosure, String>,
		JpaSpecificationExecutor<ApFileEnclosure> {
	
	/*
	 * 根据文件的对象id，找到所有的文件记录
	 */
	public List<ApFileEnclosure> findByObjIdAndDeleteState(String ObjId,String state);
	
	/**
	 * 添加文件时，根据objType,objId,fileType查询文件对应的历史记录
	 */
	@Modifying
	@Query("SELECT f from ApFileEnclosure f where f.objType = ? and f.objId = ? and propertyType = ? and f.deleteState = '0'")
	public List<ApFileEnclosure> findFilesByParams(String objType,String objId,String propertyType);
}
