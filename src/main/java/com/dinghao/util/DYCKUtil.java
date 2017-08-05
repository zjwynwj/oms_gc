package com.dinghao.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DYCKUtil {
	private static final Log log = LogFactory.getLog(DYCKUtil.class);

	//获取企业海关10位数编码: 3215461660
	public static String DYCK_QYHKCODE = "3215461660";
	// 主管海关4位编码 2300
	public static String DYCK_ZGHGCODE = "2300";
	//电商平台在国检备案后取得的唯一标示：320220160000011766
	public static String DYCK_GJBACODE = "320220160000011766";
	//电商平台在国检备案的名称：江苏悦盟投资管理有限公司 
	public static String DYCK_GJBANAME = "江苏悦盟投资管理有限公司";
	
	//public static String url = "https://221.224.53.219:8000/BCProDec";//

	public static String getMd5Code(final String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		StringBuilder hexString = new StringBuilder();
		try {
			byte[] btInput = str.getBytes("utf-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(btInput);

			byte[] hash = md.digest();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0").append(Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (Exception e) {
			return "";
		}
		return hexString.toString();
	}
    
	/*
	 * 商品备案接口
	 * */
	public static String BCProDec(String xml) throws IOException {

		String https = "https://221.224.53.219:8000/BCProDec";
		String ret = "";
		
		Element rootElement = DocumentHelper.createElement("MESSAGEROOT");
		
		Element MessageHead  = rootElement.addElement("MessageHead");
		Element MESSAGE_ID   = MessageHead.addElement("MESSAGE_ID");
		String timestamp = Long.valueOf(System.currentTimeMillis()).toString();
		String v_messageId = "CEB_201_" + DYCK_QYHKCODE + "_" + DYCK_ZGHGCODE + timestamp;
		MESSAGE_ID.setText(v_messageId);
		Element MESSAGE_TYPE = MessageHead.addElement("MESSAGE_TYPE");
		MESSAGE_TYPE.setText("201");
		Element SEND_ID = MessageHead.addElement("SEND_ID");
		SEND_ID.setText(DYCK_QYHKCODE);
		Element SEND_PWD = MessageHead.addElement("SEND_PWD");
		SEND_PWD.setText("1234");
		Element RECEIVER_ID = MessageHead.addElement("RECEIVER_ID");
		RECEIVER_ID.setText(DYCK_ZGHGCODE);
		Element SEND_TIME = MessageHead.addElement("SEND_TIME");
		SEND_TIME.setText( DateUtil.getFormatCurTime("yyyyMMddHHMMss"));
		
		Element MessageBody  = rootElement.addElement("MessageBody");
		Element PRODUCT_FILING_INFO_ID   = MessageBody.addElement("PRODUCT_FILING_INFO_ID");
		PRODUCT_FILING_INFO_ID.setText(GenerateGUID());
		Element ENT_CBEC_CODE  = MessageBody.addElement("ENT_CBEC_CODE");
		ENT_CBEC_CODE.setText(DYCK_GJBACODE);

		Element ENT_CNAME  = MessageBody.addElement("ENT_CNAME");
		ENT_CNAME.setText(DYCK_GJBANAME);

		Element HS_CODE  = MessageBody.addElement("HS_CODE");
		HS_CODE.setText("1211903500");
		
		Element SKU  = MessageBody.addElement("SKU");
		SKU.setText("3182520174953");
		
		Element PRODUCT_NAME  = MessageBody.addElement("PRODUCT_NAME");
		PRODUCT_NAME.setText("法国超级波尔多AOC拉贝丝城堡干型红葡萄酒原装原瓶进口");
		
		Element BAR_CODE  = MessageBody.addElement("BAR_CODE");
		BAR_CODE.setText("3182520174953");
		
		Element BRAND  = MessageBody.addElement("BRAND");
		BRAND.setText("波尔多");
		
		Element ENTER_OUT_FLAG  = MessageBody.addElement("ENTER_OUT_FLAG");
		ENTER_OUT_FLAG.setText("1");
	
		Element PROD_ENT  = MessageBody.addElement("PROD_ENT");
		PROD_ENT.setText("AOC拉贝丝城堡");
		
		Element PROD_COUNTRY_CODE  = MessageBody.addElement("PROD_COUNTRY_CODE");
		PROD_COUNTRY_CODE.setText("250");
	
		Element PROD_COUNTRY_NAME  = MessageBody.addElement("PROD_COUNTRY_NAME");
		PROD_COUNTRY_NAME.setText("法国");
	
		Element VALIDITY  = MessageBody.addElement("VALIDITY");
		VALIDITY.setText("三年");
	
		Element APPLY_DATE  = MessageBody.addElement("APPLY_DATE");
		APPLY_DATE.setText(DateUtil.format(new Date(), "yyyy-MM-ddTHH:mm:ss"));
	
		Element MONITOR_ORG_CODE  = MessageBody.addElement("MONITOR_ORG_CODE");
		MONITOR_ORG_CODE.setText("320220");
		
		Element TRADE_MODE  = MessageBody.addElement("TRADE_MODE");
		TRADE_MODE.setText("1");
	

	
		String xmlString = rootElement.asXML();  
		 
		ret = HttpsUnit.SendHttpsPOST(https,null, xmlString);
		return ret;
	}


	 public static final String GenerateGUID(){
	  UUID uuid = UUID.randomUUID();
	  return uuid.toString();  
	 }

	
}


