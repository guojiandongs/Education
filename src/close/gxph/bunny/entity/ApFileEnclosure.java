package close.gxph.bunny.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 图片文件存储
 * @author g
 *
 */
@Entity
@Table(name = "gxph_file")
public class ApFileEnclosure implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id = "";
	private String objType = "";
	private String objId = "";
	private String propertyType = "";
	private String fileType = "";
	private String fileName = "";
	private String fileAlias = "";
	private String pathRel = "";
	private String pathAbs = "";
	private String remark = "";
	private String userId = "";
	private Timestamp saveTime;
	private String deleteState = "";
	
	// Constructors

	/** default constructor */
	public ApFileEnclosure() {
	}

	/** minimal constructor */
	public ApFileEnclosure(String id) {
		this.id = id;
	}

	/** full constructor */
	public ApFileEnclosure(String id, String objType, String objId,
			String propertyType,String fileType,
			String fileName, String pathRel, String pathAbs, String remark,
			String userId, Timestamp saveTime, String deleteState) {
		this.id = id;
		this.objType = objType;
		this.objId = objId;
		this.propertyType = propertyType;
		this.fileType = fileType;
		this.fileName = fileName;
		this.pathRel = pathRel;
		this.pathAbs = pathAbs;
		this.remark = remark;
		this.userId = userId;
		this.saveTime = saveTime;
		this.deleteState = deleteState;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjType() {
		return this.objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileAlias() {
		return fileAlias;
	}

	public void setFileAlias(String fileAlias) {
		this.fileAlias = fileAlias;
	}

	public String getPathRel() {
		return this.pathRel;
	}

	public void setPathRel(String pathRel) {
		this.pathRel = pathRel;
	}

	public String getPathAbs() {
		return this.pathAbs;
	}

	public void setPathAbs(String pathAbs) {
		this.pathAbs = pathAbs;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getSaveTime() {
		return this.saveTime;
	}

	public void setSaveTime(Timestamp saveTime) {
		this.saveTime = saveTime;
	}

	public String getDeleteState() {
		return this.deleteState;
	}

	public void setDeleteState(String deleteState) {
		this.deleteState = deleteState;
	}

}