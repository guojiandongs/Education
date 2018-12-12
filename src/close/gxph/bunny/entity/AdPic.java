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
 * 广告(幻灯片)
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "gxph_ad")
public class AdPic implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String name;
	private String state;
	private Integer seq;
	private String memo;
	private String type;
	private String url;
	// 录入人ID
	private String recorduserid;
	// 录入时间
	private Timestamp recordtime;
	// 录入人姓名
	private String recordusername;

	@Transient
	private List<ApFileEnclosure> apFileEnclosures;
	@Transient
	private ApFileEnclosure apFileEnclosure;

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

	@Transient
	private String picurl;

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ApFileEnclosure> getApFileEnclosures() {
		return apFileEnclosures;
	}

	public void setApFileEnclosures(List<ApFileEnclosure> apFileEnclosures) {
		this.apFileEnclosures = apFileEnclosures;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ApFileEnclosure getApFileEnclosure() {
		return apFileEnclosure;
	}

	public void setApFileEnclosure(ApFileEnclosure apFileEnclosure) {
		this.apFileEnclosure = apFileEnclosure;
	}

}
