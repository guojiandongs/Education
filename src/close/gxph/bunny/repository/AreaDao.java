package close.gxph.bunny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.Area;

public interface AreaDao extends JpaRepository<Area, String>,
		JpaSpecificationExecutor<Area> {
}
