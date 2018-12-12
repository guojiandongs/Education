package close.gxph.bunny.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 数据字典
 * @author g
 *
 */
@Entity
@Table(name = "sys_dict")
public class Dict implements Serializable {
	private static final long serialVersionUID = 1L;
/*	id	1	CHAR
	word	12	VARCHAR
	code	-6	TINYINT
	value	12	VARCHAR
	des	12	VARCHAR
*/ 
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	@NotBlank
	@Length(max=32)
	private String word;
	private int code;
	@NotBlank
	@Length(max=32)
	private String value;
	@Length(max=128)
	private String des;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
}
