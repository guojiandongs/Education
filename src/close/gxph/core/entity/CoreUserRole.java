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
 * 用户角色
 *
 */
@Entity
@Table(name="sys_user_role")
public class CoreUserRole implements Serializable{
	private static final long serialVersionUID = 2038187409024861852L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String userid;
	private String roleid;
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="roleid",insertable=false,updatable=false)
	private CoreRole coreRole;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public CoreRole getCoreRole() {
		return coreRole;
	}
	public void setCoreRole(CoreRole coreRole) {
		this.coreRole = coreRole;
	}
}
