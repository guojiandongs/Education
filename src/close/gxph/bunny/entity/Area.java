package close.gxph.bunny.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 地域
 * @author g
 *
 */
@Entity
@Table(name = "sys_area")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@NotBlank
	private String code;
	private String name;
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
}
