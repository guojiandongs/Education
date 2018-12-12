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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "gxph_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String name;
	private String content;
	private String productdetail;
	private String notice;
	private String subjectid;
	private String usinessesid;
	private String informationtypeid;
	private String areacode;
	private String producttag;
	private Integer marketprice;
	private Integer sanprice;
	private Integer store;
	private Integer buynumber;
	private Integer singlenumber;
	private Integer servicepropercent;// 平台服务费百分比
	private Integer returnpropercent;// 退款收取百分比
	private String status;// 审核状态 0:未审核;1预热审核2上线审核3下架审核4:审核不通过
	private String issingular;// 核审人员0:初审1：复审 2:审核完成
	// 购买到期时间
	private Timestamp buytime;
	// 录入人ID
	private String recorduserid;
	// 录入时间
	private Timestamp recordtime;
	// 录入人姓名
	private String recordusername;

	@Transient
	private List<ApFileEnclosure> apFileEnclosureList;

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

	public Integer getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(Integer marketprice) {
		this.marketprice = marketprice;
	}

	public Integer getSanprice() {
		return sanprice;
	}

	public void setSanprice(Integer sanprice) {
		this.sanprice = sanprice;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getBuytime() {
		return buytime;
	}

	public void setBuytime(Timestamp buytime) {
		this.buytime = buytime;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getProductdetail() {
		return productdetail;
	}

	public void setProductdetail(String productdetail) {
		this.productdetail = productdetail;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public Integer getBuynumber() {
		return buynumber;
	}

	public void setBuynumber(Integer buynumber) {
		this.buynumber = buynumber;
	}

	public Integer getSinglenumber() {
		return singlenumber;
	}

	public void setSinglenumber(Integer singlenumber) {
		this.singlenumber = singlenumber;
	}

	public String getProducttag() {
		return producttag;
	}

	public void setProducttag(String producttag) {
		this.producttag = producttag;
	}

	public List<ApFileEnclosure> getApFileEnclosureList() {
		return apFileEnclosureList;
	}

	public void setApFileEnclosureList(List<ApFileEnclosure> apFileEnclosureList) {
		this.apFileEnclosureList = apFileEnclosureList;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getUsinessesid() {
		return usinessesid;
	}

	public void setUsinessesid(String usinessesid) {
		this.usinessesid = usinessesid;
	}

	public String getInformationtypeid() {
		return informationtypeid;
	}

	public void setInformationtypeid(String informationtypeid) {
		this.informationtypeid = informationtypeid;
	}

	public String getIssingular() {
		return issingular;
	}

	public void setIssingular(String issingular) {
		this.issingular = issingular;
	}

	public Integer getServicepropercent() {
		return servicepropercent;
	}

	public void setServicepropercent(Integer servicepropercent) {
		this.servicepropercent = servicepropercent;
	}

	public Integer getReturnpropercent() {
		return returnpropercent;
	}

	public void setReturnpropercent(Integer returnpropercent) {
		this.returnpropercent = returnpropercent;
	}

}
