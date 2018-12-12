package close.gxph.bunny.entity;

import java.util.Map;
import java.util.TreeMap;
import org.apache.http.entity.StringEntity;

import close.gxph.bunny.util.HttpClientUtil;
import close.gxph.bunny.util.MD5Util;
import close.gxph.core.constant.Contants;


public class WXRefund {

	private String appid;
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	private String mch_id;
	private String op_user_id;
	private String out_refund_no;
	private String out_trade_no;
	private String total_fee;
	private String refund_fee;
	private String refund_fee_type;
	
	private String nonce_str;
	private String sign;
	private String partnerKey;
	
	

	/*//请求下载对账单接口
	@SuppressWarnings("unchecked")
	public String refund() throws Exception{
		
	
		 KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	        FileInputStream instream = new FileInputStream(new File("D:/apiclient_cert.p12"));
	        try {
	            keyStore.load(instream, "1353863502".toCharArray());
	        } finally {
	            instream.close();
	        }

	        // Trust own CA and all self-signed certs
	        SSLContext sslcontext = SSLContexts.custom()    	
	                .loadKeyMaterial(keyStore, "1353863502".toCharArray())
	                .build();
	        // Allow TLSv1 protocol only
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();
		HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
		
		HttpPost httpPost = new HttpPost("");
		String xml = getPackage();
		String result = "";
		System.out.println(xml);
		StringEntity entity;
		Map<String, String> map = null;
		try {
			entity = new StringEntity(xml, "utf-8");
			httpPost.setEntity(entity);

			HttpResponse httpResponse;
			// post请求
			httpResponse = httpclient.execute(httpPost);

			// getEntity()
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				// 打印响应内容
				 result = EntityUtils.toString(httpEntity, "UTF-8");
				 System.out.println("orderquery响应内容："+result);
				 result = result.replaceAll("<![CDATA[|]]>", "");
					try {
						map = XMLUtil.doXMLParse(result);
						System.out.println("orderquery响应内容："+map.toString());
					} catch (JDOMException e) {
						e.printStackTrace();
					}
				// 过滤
			
				
			}
			// 释放资源
			httpclient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public String getPackage() {
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("appid", this.appid);
		treeMap.put("op_user_id", this.op_user_id);
		treeMap.put("out_refund_no", this.out_refund_no);
		treeMap.put("out_trade_no", this.out_trade_no);
		treeMap.put("total_fee", this.total_fee);
		treeMap.put("refund_fee", this.refund_fee);
		treeMap.put("refund_fee_type", this.refund_fee_type);
		
		treeMap.put("mch_id", this.mch_id);
		treeMap.put("nonce_str", this.nonce_str);
		StringBuilder sb = new StringBuilder();
		for (String key : treeMap.keySet()) {
			sb.append(key).append("=").append(treeMap.get(key)).append("&");
		}
		
		sb.append("key=" + partnerKey);
		sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
		treeMap.put("sign", sign);

		StringBuilder xml = new StringBuilder();
		xml.append("<xml>\n");

		for (Map.Entry<String, String> entry : treeMap.entrySet()) {
			if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
				xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue()).append("]]></" + entry.getKey() + ">\n");
			} else {
				xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
			}
		}
		xml.append("</xml>");
		return xml.toString();
	}
	
	
public String refund(String usiness) throws Exception {
		
		
		String xml = getPackage();
		StringEntity entity;
		String refund = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		HttpClientUtil HttptUtil =new HttpClientUtil();
		String result  = "";
		result = HttptUtil.sslHttpPost(refund, xml, usiness, "D:/apiclient_cert.p12");
		result = result.replaceAll("<![CDATA[|]]>", "");
		return result;
		
	}
	

}
