package close.gxph.bunny.entity;

import java.io.Serializable;


import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import close.gxph.core.entity.CoreRole;
/**
 * 系统用户
 * @author wave111
 *
 */
@Entity
@Table(name = "sys_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	@Length(max = 64)
	@NotBlank
	private String code;
	@NotBlank
	@Length(max = 32)
	private String name;
	@NotBlank
	@Length(max = 128)
	private String pwd;
	@Length(max = 16)
	private String tel;
	@Length(max = 16)
	private String mobile;
	@Length(max = 64)
	private String mail;
	@Length(max = 256)
	private String headimg;

	private Integer status;
	
	private Timestamp createdate;

	private Integer createchannel;

	private Integer type;
	@Transient
	private CoreRole coreRole;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Integer getCreatechannel() {
		return createchannel;
	}

	public void setCreatechannel(Integer createchannel) {
		this.createchannel = createchannel;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public CoreRole getCoreRole() {
		return coreRole;
	}
	public void setCoreRole(CoreRole coreRole) {
		this.coreRole = coreRole;
	}
}
