package close.gxph.bunny.util;

import net.sf.json.JSONObject;

public class StrUtil {

	private static final String BLANK = "";
	private static final String NULL_C = "NULL";
	private static final String NULL_L = "null";
	
	public final static String FULL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String SHORT_DATE_PATTERN = "yyyy-MM-dd";
	
	public static boolean isEmpty(String str) {
		return str == null || BLANK.compareTo(str) == 0 || NULL_C.compareTo(str) == 0 || NULL_L.compareTo(str) == 0;
	}
	
	public static boolean isEmpty(Object str) {
		return str == null || BLANK.equals(str);
	}
	/**
	 * 将实体对象转换为json字符串
	 * @param object
	 * @return
	 */
	public static String objectToJsonString(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object); 
		return jsonObject.toString();
	}

	public static void main(String[] args) {
		String b = "bb";
		System.out.println(b);
	}
}
