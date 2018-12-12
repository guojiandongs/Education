package close.gxph.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import close.gxph.core.entity.CoreRole;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoreRoleDao extends JpaRepository<CoreRole, String>,JpaSpecificationExecutor<CoreRole>{
	
	public CoreRole findByCode(String code);
}
