package close.gxph.core.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * @author g
 * 角色资源
 *
 */
@Entity
@Table(name="sys_role_resource")
public class CoreRoleRes implements Serializable{
	
	private static final long serialVersionUID = -3136727460126522061L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String roleid;
	private String resourceid;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="resourceid",insertable=false,updatable=false)
	private CoreRes coreRes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getResourceid() {
		return resourceid;
	}
	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}
	
	public CoreRes getCoreRes() {
		return coreRes;
	}
	public void setCoreRes(CoreRes coreRes) {
		this.coreRes = coreRes;
	}
}
