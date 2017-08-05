package com.dinghao.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dinghao.entity.htobject.resultEntity;
import com.dinghao.entity.htobject.resultEntityProduct;
import com.dinghao.entity.template.base.Shop;
import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class WSCUtil {
	private static final Log log = LogFactory.getLog(WSCUtil.class);

	public static String url = "http://wap.helloht.com/plat/";//

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

	public static resultEntity searchOrder(Shop shop, Date startTime, Date endTime, Long pageNo) throws UnsupportedEncodingException {

		String http = url + "searchOrder.do";

		String utf8nick = "";
		try {
			utf8nick = URLEncoder.encode(shop.getNickname(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String name = "tester";
		String method = "searchOrder";
		String format = "json";
		String timestamp = Long.valueOf(System.currentTimeMillis()).toString();
		String bef = shop.getNickname() + method + format + timestamp + "wap.helloht.com";
		String sign = getMd5Code(bef);

		String param = "nick=" + utf8nick + "&method=" + method + "&name=" + name + "&timestamp=" + timestamp
				+ "&format=" + format + "&sign=" + sign;

		String pageSize = "20";
		
		param = param + "&startTime=" + DateUtil.getDateTime(startTime);
		param = param + "&endTime=" + DateUtil.getDateTime(endTime);
		param = param + "&searchType=crt";
		param = param + "&pageNo=" + pageNo.toString();
		param = param + "&pageSize=" + pageSize;

		String retJson = HttpRequestUtil.sendPost(http, param, false);

		if(StringUtil.getEncoding(retJson) == "ISO-8859-1"){
			retJson = new String(retJson.getBytes("ISO-8859-1"), "UTF-8");
		}
		
		Gson gson = new Gson();
		resultEntity retobjects = gson.fromJson(retJson, resultEntity.class);

		/*
		 * if( retobjects.getTotalCount() > pageNo * 20 ) { hasNext = true;
		 * pageNo=pageNo+1; } for(resultOrder row : retobjects.getEntity()) {
		 * 
		 * 
		 * List<resultOrderDetail> rowitems = row.getDetails();
		 * System.out.print(rowitems.get(0).getTitle()); }
		 */

		return retobjects;
	}

	public static resultEntityProduct searchGoods(String shopNick, Date startTime, Date endTime, Long pageNo) {

		String http = url + "Seek.do";

		String utf8nick = "";
		try {
			utf8nick = URLEncoder.encode(shopNick, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String action = "productSeek";
		String name = "tester";
		String method = "productSeek";
		String format = "json";

		String timestamp = Long.valueOf(System.currentTimeMillis()).toString();
		String bef = shopNick + method + format + timestamp + "wap.helloht.com";
		String sign = getMd5Code(bef);

		String param = "nick=" + utf8nick + "&method=" + method + "&name=" + name + "&timestamp=" + timestamp
				+ "&format=" + format + "&sign=" + sign;

		String pageSize = "20";
		param = param + "&startTime=" + DateUtil.getDateTime(startTime);
		param = param + "&endTime=" + DateUtil.getDateTime(endTime);
		param = param + "&searchType=upd";
		param = param + "&pageNo=" + pageNo.toString();
		param = param + "&pageSize=" + pageSize;

		String retJson = HttpRequestUtil.sendPost(http, param, false);

		Gson gson = new Gson();
		resultEntityProduct retobjects = gson.fromJson(retJson, resultEntityProduct.class);

		return retobjects;
	}
	
	public static resultEntityProduct synGoodsQty(String shopNick,String gdsNo, String skuNo, Long qyt) {

		String http = url + "Seek.do";

		String utf8nick = "";
		try {
			utf8nick = URLEncoder.encode(shopNick, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String action = "productSeek";
		String name = "tester";
		String method = "synGoodsQty";
		String format = "json";

		String timestamp = Long.valueOf(System.currentTimeMillis()).toString();
		String bef = shopNick + method + format + timestamp + "wap.helloht.com";
		String sign = getMd5Code(bef);

		String param = "nick=" + utf8nick + "&method=" + method + "&name=" + name + "&timestamp=" + timestamp
				+ "&format=" + format + "&sign=" + sign;

		param = param + "&gdsNo=" + gdsNo;
		param = param + "&skuNo=" + skuNo;
		param = param + "&qyt=" + qyt.toString();
	   
	 
	    resultEntityProduct retobjects = new resultEntityProduct() ;
		retobjects.setSuccess(true);
		String retJson = HttpRequestUtil.sendPost(http, param, false);
		

		if ("".equals(retJson))
		{
			retobjects.setSuccess(false);
			retobjects.setMsg("返回错误！");
		}
		else
		{
			if (retJson.contains("html") || retJson.contains("HTML") )
			{
				retobjects.setSuccess(false);
				retobjects.setMsg("返回错误！");
			}
			else
			{
				Gson gson = new Gson();
				retobjects= gson.fromJson(retJson, resultEntityProduct.class);
			}
		}   
		
		return retobjects;
	}
	public static void sendOrder(String shopNick,String orderno, Date sendTime, String Expno, String Expcode,String ExpCompany) 
	{
		String http = url + "sendOrder.do";
		
		String utf8nick = "";
		try {
			utf8nick = URLEncoder.encode(shopNick, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String name = "tester";
		String method = "sendOrder";
		String format = "json";
		String timestamp = Long.valueOf(System.currentTimeMillis()).toString();
		String bef = shopNick + method + format + timestamp + "wap.helloht.com";
		String sign = getMd5Code(bef);

		String param = "nick=" + utf8nick + "&method=" + method + "&name=" + name + "&timestamp=" + timestamp
				+ "&format=" + format + "&sign=" + sign;
		
		param = param + "&Orderno="+orderno;
		param = param + "&sendTime="+DateUtil.format(sendTime,"yyyy-MM-dd HH:mm:ss");
		param = param + "&Expno="+Expno;
		param = param + "&Expcode="+Expcode;
		param = param + "&ExpCompany="+ExpCompany;
		try	{
			String retJson = HttpRequestUtil.sendPost(http, param, false);
			System.out.println(retJson);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
}
