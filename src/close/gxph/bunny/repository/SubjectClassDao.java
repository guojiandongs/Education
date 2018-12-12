package close.gxph.bunny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.SubjectClass;

public interface SubjectClassDao extends JpaRepository<SubjectClass, String>,
JpaSpecificationExecutor<SubjectClass>{
	List<SubjectClass> findByState(String status);
}
