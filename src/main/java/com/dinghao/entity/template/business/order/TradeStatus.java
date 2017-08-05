package com.dinghao.entity.template.business.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradeStatus  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**等待买家付款*/
	public static final String WAIT_BUYER_PAY="WAIT_BUYER_PAY";
	/**等待卖家发货*/
	public static final String WAIT_SELLER_SEND_GOODS="WAIT_SELLER_SEND_GOODS";
	/**卖家部分发货*/
	public static final String SELLER_CONSIGNED_PART="SELLER_CONSIGNED_PART";
	/**等待买家确认收货*/
	public static final String WAIT_BUYER_CONFIRM_GOODS="WAIT_BUYER_CONFIRM_GOODS";
	/**买家已签收（货到付款专用）*/
	public static final String TRADE_BUYER_SIGNED="TRADE_BUYER_SIGNED";
	/**交易成功*/
	public static final String TRADE_FINISHED="TRADE_FINISHED";
	/**交易关闭*/
	public static final String TRADE_CLOSED="TRADE_CLOSED";
	/**付款以前，卖家或买家主动关闭交易*/
	public static final String TRADE_CLOSED_BY_TAOBAO="TRADE_CLOSED_BY_TAOBAO";
	/**没有创建外部交易(支付宝交易)*/
	public static final String TRADE_NO_CREATE_PAY="TRADE_NO_CREATE_PAY";
	/**余额宝0元购合约中*/
	public static final String WAIT_PRE_AUTH_CONFIRM="WAIT_PRE_AUTH_CONFIRM";
	/**外卡支付付款确认中*/
	public static final String PAY_PENDING="PAY_PENDING";
	/**所有买家未付款的交易（包含：WAIT_BUYER_PAY、TRADE_NO_CREATE_PAY）*/
	public static final String ALL_WAIT_PAY="ALL_WAIT_PAY";
	/**所有关闭的交易（包含：TRADE_CLOSED、TRADE_CLOSED_BY_TAOBAO）*/
	public static final String ALL_CLOSED="TRADE_NO_CREATE_PAY";
	
	
	
	/**淘宝明细order 不需要考虑占用库存的状态 
	 * 1.等待买家确认收货
	 * 2.买家已签收（货到付款专用）
	 * 3.交易成功
	 * 4.交易关闭
	 * 5.付款以前，卖家或买家主动关闭交易
	 */
	public  static List<String> ORDER_NO_USE_STORE;
	
	/**淘宝所有已经发货状态
	 * 1.等待买家确认收货 WAIT_BUYER_CONFIRM_GOODS
	 * 2.买家已签收（货到付款专用） TRADE_BUYER_SIGNED
	 * 3.交易成功 TRADE_FINISHED
	 */
	public  static List<String> ORDER_ALL_SEND;
	
	
	/**淘宝所有关闭状态
	 * 1.付款以前，卖家或买家主动关闭交易  TRADE_CLOSED_BY_TAOBAO
	 * 2.付款以后用户退款成功，交易自动关闭 TRADE_CLOSED
	 */
	public  static List<String> ORDER_ALL_CLOSE;

	static {
		if(null==ORDER_NO_USE_STORE){
			ORDER_NO_USE_STORE=new ArrayList<String>();
			ORDER_NO_USE_STORE.add(WAIT_BUYER_CONFIRM_GOODS);
			ORDER_NO_USE_STORE.add(TRADE_BUYER_SIGNED);
			ORDER_NO_USE_STORE.add(TRADE_FINISHED);
			ORDER_NO_USE_STORE.add(TRADE_CLOSED);
			ORDER_NO_USE_STORE.add(TRADE_CLOSED_BY_TAOBAO);
		}
		
		if(null==ORDER_ALL_SEND){
			ORDER_ALL_SEND=new ArrayList<String>();
			ORDER_ALL_SEND.add(WAIT_BUYER_CONFIRM_GOODS);
			ORDER_ALL_SEND.add(TRADE_BUYER_SIGNED);
			ORDER_ALL_SEND.add(TRADE_FINISHED);
		}
		
		if(null==ORDER_ALL_CLOSE){
			ORDER_ALL_CLOSE=new ArrayList<String>();
			ORDER_ALL_CLOSE.add(TRADE_CLOSED_BY_TAOBAO);
			ORDER_ALL_CLOSE.add(TRADE_BUYER_SIGNED);
		}
		
	}		
		

	
}
