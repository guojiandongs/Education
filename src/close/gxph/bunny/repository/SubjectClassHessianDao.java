package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.SubjectClass;

public interface SubjectClassHessianDao{
	Page<SubjectClass> getSubjectClass(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy,String sorttype);
	SubjectClass getSub(String id);
	void deleteSub(String subId);
	String add(SubjectClass subjectClass);
	List<SubjectClass> getAllSubjectClassByStatus();
}
