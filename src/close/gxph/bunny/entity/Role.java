package close.gxph.bunny.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 角色
 * @author g
 *
 */
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	
	@NotBlank
	private String code;
	@NotBlank
	private String name;
	private String des;
	@Transient
	private String chooseval;
	public String getChooseval() {
		return chooseval;
	}

	public void setChooseval(String chooseval) {
		this.chooseval = chooseval;
	}

	/*@ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE,
			CascadeType.PERSIST }, mappedBy = "roles")

	@JoinTable(name = "sys_role_auth", inverseJoinColumns = @JoinColumn(name = "authcode"), joinColumns = @JoinColumn(name = "rolecode"))
	private Set<Auth> auths = new HashSet<Auth>();*/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


/*	public Set<Auth> getAuths() {
		return auths;
	}

	public void setAuths(Set<Auth> auths) {
		this.auths = auths;
	}*/


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
