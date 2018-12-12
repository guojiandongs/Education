package close.gxph.core.constant;


/**
 * 一些常量
 * 
 * @author Close
 */
public class Contants {
	public static final int ORDER_DAY = 5;//订单自动结算天数
	public static final String PAGE_SIZE = "15";//分页参数
	public static final int PAGE_SIZES = 15;
	public static final String CURRENT_USER = "current_user";//登录用户
	public static final String UPLOAD_PERCERT = "upload_percent";
	public static final String ROLE_CODE = "role_code";//登录用户角色
	public static final String CURRENT_US = "current_us";//登录商户
	public static final String SHANXICODE = "140000";//山西省code
	//微信授权
	public static String appid="wxb76a14212c990b3c";
	public static String secret="2be147f92bc9309e5ab5796e41e438bf";
	public static String TOKEN = "shanxilvyougxph";
	public static String HESSIAN_URL = "http://localhost:8080/SxLyHessian/wxweb/service/";
	public static String PAY_USINESSES = "1364785502";//微信支付商户号
	public static String PAY_KEY = "jeQrBqu3bTAlIPjknU1GI3oZNMWeAUI3";//微信支付秘钥
	
	//登陆用户-----管理员
	public static String USER_TYPE_ADMIN = "1";
	//登陆用户-----商户
	public static String USER_TYPE_USINE = "2";
	//登陆用户-----初审员
	public static String USER_TYPE_START = "3";
	//登陆用户-----复审员
	public static String USER_TYPE_REPEAT = "4";
	//删除状态（delete_state）-未删除
	public static String DEL_STATE_NO = "0";
	//删除状态（delete_state）-已删除
	public static String DEL_STATE_YES = "1";
	//启用状态（delete_state）-停用
	public static String STATE_NO = "0";
	//启用状态（delete_state）-启用
	public static String STATE_YES = "1";
	//是否属于资讯（delete_state）-不属于
	public static String BELONG_STATE_NO = "0";
	//是否助于资讯（delete_state）-属于
	public static String BELONG_STATE_YES = "1";
	//审核状态（delete_state）-没有审核
	public static String CHECK_STATE_NO = "0";
	//审核状态（delete_state）-预热审核
	public static String CHECK_STATE_ONE = "1";
	//审核状态（delete_state）-已预热
	public static String CHECK_STATE_TWO = "2";
	//审核状态（delete_state）-上线审核
	public static String CHECK_STATE_THREE = "3";
	//审核状态（delete_state）-已上线
	public static String CHECK_STATE_FOUR = "4";
	//审核状态（delete_state）-下架审核
	public static String CHECK_STATE_FIVE = "5";
	//审核状态（delete_state）-已下架
	public static String CHECK_STATE_SIX  = "6";
	//审核状态（delete_state）-审核不通过
	public static String CHECK_STATE_SEVEN  = "7";
	//附件类别（file_type）-文本
	public static String FILE_TYPE_TEXT = "0";
	//审核状态（delete_state）-初审员
	public static String CHECK_VALIDATE_NO = "0";
	//审核状态（delete_state）-复审员
	public static String CHECK_VALIDATE_ONE = "1";
	//审核状态（delete_state）-审核完成
	public static String CHECK_VALIDATE_TWO = "2";
	//图片幻灯片类型
	public static String FILE_NEWS_SLIDE = "NEWS_SLIDE";
	
	//对象类别（obj_type）-幻灯片（广告）
	public static String OBJ_TYPE_AD = "AD";
	//对象类别（obj_type）-主题分类
	public static String OBJ_TYPE_ZTFL = "ZTFL";
	//对象类别（obj_type）-资讯
	public static String OBJ_TYPE_ZX = "ZX";
	//对象类别（obj_type）-分区管理
	public static String OBJ_TYPE_FQGL = "FQGL";
	//对象类别（obj_type）-product产品
	public static String OBJ_TYPE_PRODUCT = "PRODUCT";
	//订单状态--未支付
	public static String ORDER_STATUS_ON = "1";
}
