package close.gxph.bunny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.Area;
import close.gxph.bunny.entity.Rate;

public interface RateDao extends JpaRepository<Rate, String>,
		JpaSpecificationExecutor<Rate> {
}
