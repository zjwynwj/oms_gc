package com.dinghao.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.api.ApiException;
import com.taobao.api.ClusterTaobaoClient;
import com.taobao.api.TaobaoClient;

public class TBUtil {
	private static final Log log = LogFactory.getLog(TBUtil.class);

	public static String url="http://gw.api.taobao.com/router/rest";//
	
	private static TaobaoClient client=null;
	
	/**
	 * @category 获取淘宝请求client
	 * @return TaobaoClient
	 */
	public static TaobaoClient getTaobaoClient(String appkey , String secret){
		try {
			client=new ClusterTaobaoClient(url, appkey, secret);
		} catch (ApiException e) {
			log.error("初始化淘宝client失败:init taobao client failure", e);
		}
		return client;
	}
}
