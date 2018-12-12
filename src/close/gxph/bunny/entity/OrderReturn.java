package close.gxph.bunny.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 退单
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "gxph_order_return")
public class OrderReturn implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String notest;
	private String tno;
	private String reasons;
	private String orderid;
	private Timestamp returntime;
	private String state;
	private String returnmoney;
	private String realmoney;
	@OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "orderid", insertable = false, updatable = false)
	private Order order;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNotest() {
		return notest;
	}

	public void setNotest(String notest) {
		this.notest = notest;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Timestamp getReturntime() {
		return returntime;
	}

	public void setReturntime(Timestamp returntime) {
		this.returntime = returntime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getReturnmoney() {
		return returnmoney;
	}

	public void setReturnmoney(String returnmoney) {
		this.returnmoney = returnmoney;
	}

	public String getRealmoney() {
		return realmoney;
	}

	public void setRealmoney(String realmoney) {
		this.realmoney = realmoney;
	}

}
