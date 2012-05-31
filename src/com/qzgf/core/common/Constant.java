package com.qzgf.core.common;
/**
 * 系统常量
 * @author lsr
 *
 */
public class Constant{
	public static String ROLE_LIST_SESSION = "loginRoles";
	public static String LOGIN_SESSION_NAME="UserInfo";//存储用户信息的HashMap
	public static String CONTEXT_SESSION_NAME = "CONTEXT_SESSION_NAME";
	public static String VERIFY_SESSION_NAME="verifycode";//用户登录验证码
	
	public static final String USER_SESSION_KEY = "user_session";//user_session_key里面放用户的session信息
	public static String SERVLET_MAPPING = "*.do";
	public static final String VSERION = "1.0.0";
	
	/**
	 * 上传excel文件的存入路径(宝峰鞋业电子商务) http://www.ems11183.com/net/uploadExcle.do 
	 * */
	public static String ROOTPATH = "d://safefile/";
	//服务器:"/opt/apache-tomcat-6.0.16/webapps/ROOT/excel/""d://safefile/" 本地:d://safefile/
	//用于用户下载,此功能暂时不需要
	/*在tomcat的conf下的server.xml做下映射如下
	 <Host name="localhost" appBase="webapps"
       unpackWARs="true" autoDeploy="true"
       xmlValidation="false" xmlNamespaceAware="false">
     关键此行 <Context path="/photo" docBase="d://safefile/"/> 
      </Host>
    */
	//网上寄件中的帐号 print 123456
	public static String NetUser_Excel = "2010081700000146";///创建人  服务器2010081700000146,本地2010081700000104	
	
	/**
	 * 福建省公安出入境webservice服务
	 * */
	//http://218.85.72.214:9080/PostalService/postalService.ws?wsdl
	//http://crj.fjgat.gov.cn:9080/PostalService/postalService.ws?wsdl
	//账号和密码
	public static String User_Immigration = "emsusername";
	public static String Pwd_Immigration = "emspassword";
	public static String Parm_Immigration="-1"; //测试使用116,正式环境使用-1
	//网上寄件中的帐号 gacrjgl gacrjgl
	public static String NetUser_Immigration="2010062800000132"; //测试使用"",正式环境使用2010062800000132
	
	
	/**
	 * 中国移动12580速递邮政webservice服务
	 * */
	//http://www.59112580.com/PostalService.asmx?WSDL
	//http://220.162.244.230/PostalService.asmx?WSDL
	//账号和密码(规则还需要加入日期格式为)
	//【username=MD5（用户名+日期），日期格式：yyyyMMddHH】
	//【password=MD5（密码+日期），日期格式：yyyyMMddHH】
	public static String User_Yd12580 = "12580postal";
	public static String Pwd_Yd12580 = "12580postal";
	public static String Parm_Yd12580=null; //暂时为空
	//网上寄件中的帐号 yd12580 yd12580
	//select to_char(id) from t_archives_user where name='yd12580';
	public static String NetUser_Yd12580="2010091600000106"; //测试使用"2010091600000106",正式环境使用2010091400000182  
	
	/**
	 * 打单系统,使用的模板连接地址
	 */
	public static String PrintUrl="http://www.ems11183.com/x.fr3";//正式http://www.ems11183.com/x.fr3 本地:http://127.0.0.1/x.fr3

	//证件不合格
	/**
	 * 与派揽系统通信结口地址
	 */
	public static String EmsUrl="http://127.0.0.1:8080/net/communicate.do";//正式http://10.214.6.40/netorder.aspx 本地:http://127.0.0.1:8080/net/communicate.do
}