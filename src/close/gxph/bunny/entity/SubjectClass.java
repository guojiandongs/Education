package close.gxph.bunny.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 产品分类
 * @author g
 *
 */
@Entity
@Table(name = "gxph_subject")
public class SubjectClass implements Serializable {
	private static final long serialVersionUID = -1837926431259030320L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String name;
	private String state;
	private Integer seq;
	private String content;
	
	@Transient
	private ApFileEnclosure apFileEnclosure;

	// 录入人ID
	private String recorduserid;
	// 录入时间
	private Timestamp recordtime;
	// 录入人姓名
	private String recordusername;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

}
