package close.gxph.bunny.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 费率
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "gxph_rate")
public class Rate implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String ratename;
	private String ratecode;
	private String rateway;
	private String ratestrategy;
	private String ratevalue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRatename() {
		return ratename;
	}

	public void setRatename(String ratename) {
		this.ratename = ratename;
	}

	public String getRatecode() {
		return ratecode;
	}

	public void setRatecode(String ratecode) {
		this.ratecode = ratecode;
	}

	public String getRateway() {
		return rateway;
	}

	public void setRateway(String rateway) {
		this.rateway = rateway;
	}

	public String getRatestrategy() {
		return ratestrategy;
	}

	public void setRatestrategy(String ratestrategy) {
		this.ratestrategy = ratestrategy;
	}

	public String getRatevalue() {
		return ratevalue;
	}

	public void setRatevalue(String ratevalue) {
		this.ratevalue = ratevalue;
	}

}
