package close.gxph.bunny.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 审核
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "gxph_validat")
public class Validate implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private Integer state;// 核审状态0:预热初审1：预热复审2：上线初审3：上线复审4下架初审5：下架复审6:审核不通过
	private String reason;
	private String productid;
	// 录入人ID
	private String recorduserid;
	// 录入时间
	private Timestamp recordtime;
	// 录入人姓名
	private String recordusername;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRecorduserid() {
		return recorduserid;
	}

	public void setRecorduserid(String recorduserid) {
		this.recorduserid = recorduserid;
	}

	public Timestamp getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(Timestamp recordtime) {
		this.recordtime = recordtime;
	}

	public String getRecordusername() {
		return recordusername;
	}

	public void setRecordusername(String recordusername) {
		this.recordusername = recordusername;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

}
