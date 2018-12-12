package close.gxph.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import close.gxph.bunny.entity.ApFileEnclosure;

/**
 * 菜单权限
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "sys_menutree")
public class CoreRes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String name;
	private String url;
	private Integer xh;
	private String menuflag;
	private String parentid;
	private String state;
	private String leaf;
	private Integer levels;
	@Transient
	private List<CoreRes> childcoreReslists =new ArrayList<CoreRes>();;

	@Transient
	private boolean checked;

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
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public String getMenuflag() {
		return menuflag;
	}

	public void setMenuflag(String menuflag) {
		this.menuflag = menuflag;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<CoreRes> getChildcoreReslists() {
		return childcoreReslists;
	}

	public void setChildcoreReslists(List<CoreRes> childcoreReslists) {
		this.childcoreReslists = childcoreReslists;
	}

}
