package close.gxph.bunny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.Dict;

public interface DictDao extends JpaRepository<Dict, String>,
		JpaSpecificationExecutor<Dict> {
	List<Dict> findAllByWord(String keyword);
    List<Dict> findAllByValue(String value);
}
