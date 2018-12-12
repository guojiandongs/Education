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
 * 分区设置
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "gxph_information_type")
public class InformationType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String content;
	private Integer seq;
	private String name;
	private String isbelongtype;//是否属于资讯 0:不属于;1属于
	private String status;//状态 0:停用;1正常
	// 录入人ID
	private String recorduserid;
	// 录入时间
	private Timestamp recordtime;
	// 录入人姓名
	private String recordusername;

	/*
	 * @Transient private List<ApFileEnclosure> apFileEnclosures;
	 */
	@Transient
	private ApFileEnclosure apFileEnclosure;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public ApFileEnclosure getApFileEnclosure() {
		return apFileEnclosure;
	}

	public void setApFileEnclosure(ApFileEnclosure apFileEnclosure) {
		this.apFileEnclosure = apFileEnclosure;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsbelongtype() {
		return isbelongtype;
	}

	public void setIsbelongtype(String isbelongtype) {
		this.isbelongtype = isbelongtype;
	}

}
