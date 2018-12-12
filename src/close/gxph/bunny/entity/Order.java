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
 * 订单
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "gxph_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	private String id;
	private String orderno;
	private String wxorderno;
	private String currentpay;
	private String realpay;
	private Timestamp odate;
	private String step;
	private String productid;
	private String num;
	private String buyusername;
	private String buylicenceno;
	private String buyuserphone;
	private String pickticketcode;
	@OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "productid", insertable = false, updatable = false)
	private Product product;

	private String guestid;
	private String isuse;
	private String jsparam;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getWxorderno() {
		return wxorderno;
	}

	public void setWxorderno(String wxorderno) {
		this.wxorderno = wxorderno;
	}

	public String getCurrentpay() {
		return currentpay;
	}

	public void setCurrentpay(String currentpay) {
		this.currentpay = currentpay;
	}

	public String getRealpay() {
		return realpay;
	}

	public void setRealpay(String realpay) {
		this.realpay = realpay;
	}

	public Timestamp getOdate() {
		return odate;
	}

	public void setOdate(Timestamp odate) {
		this.odate = odate;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPickticketcode() {
		return pickticketcode;
	}

	public void setPickticketcode(String pickticketcode) {
		this.pickticketcode = pickticketcode;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getGuestid() {
		return guestid;
	}

	public void setGuestid(String guestid) {
		this.guestid = guestid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBuyusername() {
		return buyusername;
	}

	public void setBuyusername(String buyusername) {
		this.buyusername = buyusername;
	}

	public String getBuyuserphone() {
		return buyuserphone;
	}

	public void setBuyuserphone(String buyuserphone) {
		this.buyuserphone = buyuserphone;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getBuylicenceno() {
		return buylicenceno;
	}

	public void setBuylicenceno(String buylicenceno) {
		this.buylicenceno = buylicenceno;
	}

	public String getJsparam() {
		return jsparam;
	}

	public void setJsparam(String jsparam) {
		this.jsparam = jsparam;
	}

}
