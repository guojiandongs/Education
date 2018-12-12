package close.gxph.bunny.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import close.gxph.bunny.entity.Guest;

public interface GuestHessianDao{
	Page<Guest> getGuests(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortBy);
	void deleteGuest(String id);
	Guest addGuest(Guest guest);
	Guest getGuest(String id);
	Guest alterGuest(Guest guest);
	List<Guest> findAll();
	List<Guest> findAllByUsername(String name);
}
