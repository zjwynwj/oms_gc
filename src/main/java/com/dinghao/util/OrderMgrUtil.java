package com.dinghao.util;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.entity.template.business.order.PageResult;
import com.dinghao.entity.template.business.order.TotalResult;
import com.dinghao.exception.DHBizException;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Trade;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.TradesSoldIncrementGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;

public class OrderMgrUtil extends TBUtil{
	
	private static final Log log = LogFactory.getLog(OrderMgrUtil.class);

	/*public static String appkey="23185404";//正式数据
	public static String secret="e45c596583a7f7e17d6b6e8092a41ffc";//正式数据		
	public static String url="http://gw.api.taobao.com/router/rest";//
*/	
	/**淘宝主表字段*/
	public static String field1="tid,buyer_nick,receiver_name,receiver_mobile,receiver_address,buyer_message,seller_memo,created,"
			+ "post_fee,adjust_fee,payment,seller_flag,buyer_memo,receiver_state,receiver_city,receiver_district,"
			+ "discount_fee,total_fee,title,receiver_zip,has_post_fee,invoice_kind,invoice_type,invoice_name,pay_time,"
			+ "est_con_time,shipping_type,receiver_phone,type,modified,status,has_buyer_message";
	/**淘宝明细字段*/
	public static String field2="orders.oid,orders.outer_sku_id,orders.outer_iid,orders.num,orders.price,"
			+ "orders.discount_fee,orders.total_fee,orders.payment,orders.title,orders.sku_properties_name,orders.sku_id, "
			+ "orders.status,orders.refund_id,orders.refund_status";
	

	/**
	 * @category 获取淘宝订单获取接口Request
	 * @return TradesSoldGetRequest
	 */
	public static TradesSoldGetRequest getTradesSoldGetRequest(){
		TradesSoldGetRequest req=new TradesSoldGetRequest();
		req.setFields(field1+","+field2);
		return req;
		
	}
	
	/**
	 * @category 获取淘宝增量订单获取接口Request
	 * @return TradesSoldGetRequest
	 */
	public static TradesSoldIncrementGetRequest getTradesSoldIncrementGetRequest() {
		TradesSoldIncrementGetRequest req=new TradesSoldIncrementGetRequest();
		req.setFields(field1+","+field2);
		return req;
	}
	
	/**
	 * @author helong
	 * @category 非分页请求 --获取总条目数
	 * @return
	 * @throws ApiException 
	 */
 	public static TotalResult getTotalNum(Date startTime,Date endTime,String sessionKey,String appkey , String secret) throws ApiException{
		/**非分页请求 --获取总条目数*/
 		TotalResult result=new TotalResult();//返回结果
 		TaobaoClient client=OrderMgrUtil.getTaobaoClient(appkey , secret);//获取请求client
		TradesSoldGetRequest reqGetTotal=OrderMgrUtil.getTradesSoldGetRequest();//为了获取总条目数发起的 第一页取一条 非分页的查询
		reqGetTotal.setStartCreated(startTime);
		reqGetTotal.setEndCreated(endTime);
		reqGetTotal.setPageNo(1l);
		reqGetTotal.setPageSize(1l);
		reqGetTotal.setUseHasNext(false);//非分页
		TradesSoldGetResponse responseGetTotal= client.execute(reqGetTotal , sessionKey);
		if(responseGetTotal.isSuccess()){
			result.setSuccess(true);
			result.setTotalNum(responseGetTotal.getTotalResults());
		}else{
			String erroCode=responseGetTotal.getErrorCode();
			if(erroCode.equals("27")){
				throw new DHBizException(ExceptionConstant.ERR_DH000002);
			}else{
				throw new DHBizException(ExceptionConstant.ERR_DH000003);
			}
		}
		return result;
	}
 	
 	/**
	 * @author helong
	 * @category 分页请求 --获取订单  根据订单创建时间
	 * @return
	 * @throws ApiException 
	 */
 	public static PageResult getTrades(Date startTime  ,Date endTime , String sessionKey ,String appkey , String secret, Boolean hasNext, Long page, Long pageSize) throws ApiException{
 		PageResult result=new PageResult();
		/**分页请求--分页获取订单数目*/
		TradesSoldGetRequest req = OrderMgrUtil.getTradesSoldGetRequest();//分页请求
		req.setStartCreated(startTime);
		req.setEndCreated(endTime);
		req.setPageNo(page);
		req.setPageSize(pageSize);
		req.setUseHasNext(true);
		
 		TaobaoClient client = OrderMgrUtil.getTaobaoClient(appkey, secret);//获取请求client
		TradesSoldGetResponse response =client.execute(req , sessionKey);
		
		if(response.isSuccess()){
			result.setSuccess(true);
			List list= response.getTrades();
			result.setList(list);
			result.setHasNext(response.getHasNext());
		}else{
			String erroCode=response.getErrorCode();
			if(erroCode.equals("27")){
				throw new DHBizException(ExceptionConstant.ERR_DH000002);
			}else{
				throw new DHBizException(ExceptionConstant.ERR_DH000003);
			}
		}
		return result;
 	}
 	//获取买家留言或者备注信息
 	/**
 	 * 根据tid sessionkey 获取订单完整信息中买家留言 卖家备注
 	 * @param tid
 	 * @param sessionKey
 	 * @return
 	 * @throws ApiException
 	 */
 	public static Trade getFullTrade(Long tid,String sessionKey,String appkey , String secret){
 		try {
			TradeFullinfoGetRequest req = new TradeFullinfoGetRequest();
			req.setFields("tid,type,status,payment,seller_memo,buyer_message");
			req.setTid(tid);
			TradeFullinfoGetResponse response = getTaobaoClient(appkey, secret).execute(req, sessionKey);
			if(response.isSuccess()){
				return response.getTrade();
			}else{
				throw new DHBizException(ExceptionConstant.ERR_DH000003);
			}
		} catch (NumberFormatException e) {
			log.error(tid+"转换为long出错!", e);
		} catch (ApiException e) {
			log.error("淘宝调用接口失败", e);
		}
		return null;
 	}
}
