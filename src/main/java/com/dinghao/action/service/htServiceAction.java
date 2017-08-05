package com.dinghao.action.service;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dinghao.action.template.BaseAction;
import com.dinghao.dao.template.business.goods.GdsInfoDao;
import com.dinghao.dao.template.locstock.LocStockDao;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.GdsInfo;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.entity.vo.template.business.goods.GdsInfoVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderListVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.service.template.base.ShopService;
import com.dinghao.service.template.business.goods.GdsInfoService;
import com.dinghao.service.template.business.order.ISalesOrderService;
import com.dinghao.service.template.locstock.LocStockService;
import com.dinghao.util.DateUtil;
import com.dinghao.util.WSCUtil;

@Controller
@RequestMapping("/service")
public class htServiceAction extends BaseAction {

	@Autowired
	private ISalesOrderService salesOrderService;
	@Autowired
	private LocStockService locStockService;
	@Autowired
	private GdsInfoService gdsInfoService;
	@Autowired
	private GdsInfoDao gdsDao;
	@Autowired
	ShopService shopService;
	@Autowired
	LocStockDao locStockDao;
	@RequestMapping(value = "/searchOrder.do", method = RequestMethod.POST)
	public void searchOrder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			SalesOrderVo salesOrderVo) throws Exception {

		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// response.setContentType("text/xml; charset=UTF-8");
		response.setContentType("application/xml; charset=UTF-8");

		Document document = (Document) DocumentHelper.createDocument();
		Element rootElement = DocumentHelper.createElement("tradelist_response");
		document.setRootElement(rootElement);

		String userid = request.getParameter("userid");
		String appkey = request.getParameter("appkey");
		String method = request.getParameter("method");
		String format = request.getParameter("format");
		String timestamp = request.getParameter("timestamp");
		String sign = request.getParameter("sign");

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		String pageSize = request.getParameter("pageSize");
		String pageIndex = request.getParameter("pageIndex");

		Date startTimed = DateUtil.getDate(startTime);
		Date endTimed = DateUtil.getDate(endTime);

		String signDe = WSCUtil.getMd5Code(userid + appkey + method + format + timestamp);

		if (!signDe.equals(sign)) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("1");
			ele = rootElement.addElement("return_msg");
			ele.setText("签名不对：" + signDe);
		} else if (Math.abs(DateUtil.getMillis(new Date()) - DateUtil.getMillis(DateUtil.getDate(timestamp)))
				/ (1000 * 60 * 60) > 30) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("2");
			ele = rootElement.addElement("return_msg");
			ele.setText("时间戳失效");
		}/* else if (DateUtil.diffDate(endTimed, startTimed) < 0 || DateUtil.diffDate(endTimed, startTimed) > 30) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("3");
			ele = rootElement.addElement("return_msg");
			ele.setText("日期不对，或日期范围不对，不能超过1个月");
		} */else {
			CommResponse commResponseOrderList = new CommResponse();
			salesOrderVo.setPageNum(Integer.parseInt(pageIndex));
			salesOrderVo.setRows(Integer.parseInt(pageSize));
			salesOrderVo.setPlatStatus(status);
			salesOrderVo.setStartTime(null);
			salesOrderVo.setEndTime(null);
			salesOrderVo.setHasfaudit("1");
			salesOrderVo.setHassend("0");
			salesOrderVo.setTimestamp(null);

			commResponseOrderList = salesOrderService.findSalesOrderForGrid(salesOrderVo);
			rootElement = createXML(rootElement, commResponseOrderList);
		}
		String xmlString = rootElement.asXML();
		out.print(xmlString);
		out.flush();
		out.close();

	}

	private Element createXML(Element rootElement, CommResponse list) {

		@SuppressWarnings("unchecked")
		PageVo<SalesOrderVo> pvSalesorder = (PageVo<SalesOrderVo>) list.getData();
		List<SalesOrderVo> salesOrderVoList = pvSalesorder.getList();

		Element suc_ele = rootElement.addElement("return_code");
		suc_ele.setText("0");

		Element msg_ele = rootElement.addElement("return_msg");
		msg_ele.setText("成功");

		msg_ele = rootElement.addElement("total_num");
		msg_ele.setText(pvSalesorder.getCount() + "");

		Element pageno_ele = rootElement.addElement("page_index");
		pageno_ele.setText(pvSalesorder.getPageNum() + "");

		Element pagesize_ele = rootElement.addElement("page_num");
		pagesize_ele.setText(salesOrderVoList.size() + "");

		for (SalesOrderVo salesOrderVo : salesOrderVoList) {

			Element edept = rootElement.addElement("trade");
			edept.addElement("created").addText(DateUtil.getDateTime(salesOrderVo.getCreateDate()) + "");
			edept.addElement("modified").addText(salesOrderVo.getModifyDate() + "");
			edept.addElement("pay_time").addText(DateUtil.getDateTime(salesOrderVo.getPayTime()) + "");
			edept.addElement("seller_nick").addText(salesOrderVo.getSellerNick());
			edept.addElement("seller_id").addText(salesOrderVo.getShopId().toString());
			edept.addElement("status").addText(salesOrderVo.getPlatStatus());
			edept.addElement("plan_type").addText(salesOrderVo.getPlatType());
			edept.addElement("tid").addText(salesOrderVo.getTopTids());
		}

		return rootElement;
	}

	@RequestMapping(value = "/searchOrderDetail.do", method = RequestMethod.POST)
	public void searchOrderDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			SalesOrderVo salesOrderVo) throws Exception {

		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		response.setContentType("text/xml; charset=UTF-8");
		Document document = (Document) DocumentHelper.createDocument();
		Element rootElement = DocumentHelper.createElement("orderdetail_response");
		document.setRootElement(rootElement);

		String userid = request.getParameter("userid");
		String appkey = request.getParameter("appkey");
		String method = request.getParameter("method");
		String format = request.getParameter("format");
		String timestamp = request.getParameter("timestamp");
		String sign = request.getParameter("sign");

		String tid = request.getParameter("tid");

		String signDe = WSCUtil.getMd5Code(userid + appkey + method + format + timestamp);

		if (!signDe.equals(sign)) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("1");
			ele = rootElement.addElement("return_msg");
			ele.setText("签名不对：" + signDe);
		} else if (Math.abs(DateUtil.getMillis(new Date()) - DateUtil.getMillis(DateUtil.getDate(timestamp)))
				/ (1000 * 60 * 60) > 30) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("2");
			ele = rootElement.addElement("return_msg");
			ele.setText("时间戳失效");
		} else {
			// CommResponse commResponseOrderitem = new CommResponse();
			salesOrderVo.setTopTids(tid);
			salesOrderVo.setTimestamp(null);

			List<SalesOrderVo> salesOrderList = salesOrderService.querySalesOrderbyTid(tid);
			if (null == salesOrderList) {
				Element ele = rootElement.addElement("return_code");
				ele.setText("3");
				ele = rootElement.addElement("return_msg");
				ele.setText("订单无效");
			} else {
				salesOrderVo = salesOrderList.get(0);
				SalesOrderVo salesOrderVo2 = new SalesOrderVo();
				salesOrderVo2.setId(salesOrderVo.getId());
				CommResponse commResponseOrderitem = salesOrderService.querySalesOrderItemList(salesOrderVo2);
				@SuppressWarnings("unchecked")
				List<SalesOrderitemVo> salesOrderItemVoList = (List<SalesOrderitemVo>) commResponseOrderitem.getData();
				salesOrderVo = salesOrderService.querySalesOrder(salesOrderVo);
				Long itemQty = 0L;
				for (SalesOrderitemVo salesOrderitemVo : salesOrderItemVoList) {
					itemQty += salesOrderitemVo.getQty();
				}

				Element suc_ele = rootElement.addElement("success");
				suc_ele.setText("true");
				Element msg_ele = rootElement.addElement("msg");
				msg_ele.setText("成功");

				Element trade = rootElement.addElement("trade");
				trade.addElement("reg_name").addText(salesOrderVo.getCustNick() + "");
				trade.addElement("identity_card").addText(salesOrderVo.getIdentityCard() + "");
				trade.addElement("reg_mobile").addText(salesOrderVo.getRecvmobile() + "");
				trade.addElement("buyer_email").addText(salesOrderVo.getRecvmobile() + "");
				trade.addElement("point_fee").addText(salesOrderVo.getOrderPoint() + "");
				trade.addElement("tid").addText(salesOrderVo.getTopTids() + "");
				trade.addElement("invoicetitle").addText(salesOrderVo.getInvoicememo() + "");
				trade.addElement("way").addText("1");
				trade.addElement("ordertype").addText("1");
				trade.addElement("num").addText(itemQty + "");
				trade.addElement("total_fee").addText(salesOrderVo.getTotalFee() + "");
				trade.addElement("adjust_fee").addText(salesOrderVo.getAdjustfee() + "");
				trade.addElement("payment").addText(salesOrderVo.getPayedMoney() + "");
				trade.addElement("post_fee").addText(salesOrderVo.getDeliveryfee() + "");
				trade.addElement("TaxAmount").addText(salesOrderVo.getTaxAmount() + "");
				trade.addElement("created").addText(DateUtil.getDate(salesOrderVo.getCreateDate()) + "");
				trade.addElement("modified").addText(DateUtil.getDate(salesOrderVo.getModifyDate()) + "");
				trade.addElement("pay_time").addText(DateUtil.getDate(salesOrderVo.getPayTime()) + "");
				trade.addElement("buyer_nick").addText(salesOrderVo.getCustNick() + "");
				trade.addElement("receiver_state").addText(salesOrderVo.getProv() + "");
				trade.addElement("receiver_city").addText(salesOrderVo.getCity() + "");
				trade.addElement("receiver_district").addText(salesOrderVo.getCounty() + "");
				trade.addElement("receiver_address").addText(salesOrderVo.getAddress() + "");
				trade.addElement("receiver_zip").addText(salesOrderVo.getZipcode() + "");
				trade.addElement("receiver_name").addText(salesOrderVo.getRecvname() + "");
				trade.addElement("receiver_mobile").addText(salesOrderVo.getRecvmobile() + "");
				trade.addElement("receiver_phone").addText(salesOrderVo.getRecvphone() + "");
				trade.addElement("seller_name").addText(salesOrderVo.getSellerNick() + "");
				trade.addElement("status").addText(salesOrderVo.getPlatStatus());
				trade.addElement("alipay_no").addText("");
				trade.addElement("buyer_alipay_no").addText("");
				trade.addElement("alipay_source").addText("01");

				Element orderListe = rootElement.addElement("orders ");

				orderListe.addAttribute(new QName("list"), "true");

				for (SalesOrderitemVo salesOrderitemVo : salesOrderItemVoList) {

					Element ordere = orderListe.addElement("order");
					ordere.addElement("tid").addText(salesOrderVo.getTopTids() + "");
					ordere.addElement("adjust_fee").addText("0");
					ordere.addElement("discount_fee").addText(
							salesOrderitemVo.getSalsePrice().multiply(new BigDecimal(salesOrderitemVo.getQty()))
									.subtract(salesOrderitemVo.getTotsalMoney()) + "");
					ordere.addElement("num").addText(salesOrderitemVo.getQty() + "");
					ordere.addElement("oid").addText(salesOrderitemVo.getOid() + "");
					ordere.addElement("outer_sku_id").addText(null==salesOrderitemVo.getOuterIid()?"":salesOrderitemVo.getOuterIid() + "");
					ordere.addElement("payment").addText(salesOrderitemVo.getTotsalMoney() + "");
					ordere.addElement("price").addText(salesOrderitemVo.getSalsePrice() + "");
					ordere.addElement("sku_id").addText(salesOrderitemVo.getTopSkuid() + "");
					ordere.addElement("title").addText(salesOrderitemVo.getGdsName() + "");
					ordere.addElement("total_fee").addText(salesOrderitemVo.getTotsalMoney() + "");

				}

				// rootElement = createXML(rootElement,commResponseOrderList);
			}
		}
		String xmlString = rootElement.asXML();
		out.print(xmlString);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/sendOrder.do", method = RequestMethod.POST)
	public void orderSend(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		response.setContentType("text/xml; charset=UTF-8");
		Document document = (Document) DocumentHelper.createDocument();
		Element rootElement = DocumentHelper.createElement("order_send_response");
		document.setRootElement(rootElement);

		String userid = request.getParameter("userid");
		String appkey = request.getParameter("appkey");
		String method = request.getParameter("method");
		String format = request.getParameter("format");
		String timestamp = request.getParameter("timestamp");

		String tid = request.getParameter("tid");
		String expid = request.getParameter("expid");
		String expnumber = request.getParameter("expnumber");

		String sign = request.getParameter("sign");
		String signDe = WSCUtil.getMd5Code(userid + appkey + method + format + timestamp);
		if (!signDe.equals(sign)) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("1");
			ele = rootElement.addElement("return_msg");
			ele.setText("签名不对"+ signDe);
		} else if (Math.abs(DateUtil.getMillis(new Date()) - DateUtil.getMillis(DateUtil.getDate(timestamp)))
				/ (1000 * 60 * 60) > 30) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("2");
			ele = rootElement.addElement("return_msg");
			ele.setText("时间戳失效");
		} else {
			// 开始发货
			SalesOrderListVo salesOrderListVo = new SalesOrderListVo();
			List<SalesOrderVo> salesOrderList = new ArrayList<SalesOrderVo>();
			salesOrderList = salesOrderService.querySalesOrderbyTid(tid);
			if(null!=salesOrderList && salesOrderList.size()>0)
			{
				for (SalesOrderVo salesOrderVo : salesOrderList) {
					salesOrderVo.setExpid(Long.parseLong(expid));
					salesOrderVo.setExpcode(expnumber);
					salesOrderService.updateSalesOrder(salesOrderVo);
				}
				salesOrderListVo.setSalesOrderList(salesOrderList);

				CommResponse commResponse = salesOrderService.deliverGoods(salesOrderListVo);
				if( commResponse.isSuccess() )
				{
				
				Element suc_ele = rootElement.addElement("return_code");
				suc_ele.setText("0");
				Element msg_ele = rootElement.addElement("return_msg");
				msg_ele.setText("发货成功");
				Element item = rootElement.addElement("item");
				item.addElement("tid").addText(tid);
				item.addElement("expid").addText(expid);
				item.addElement("expnumber").addText(expnumber);
				}
				else
				{
					Element ele = rootElement.addElement("return_code");
					ele.setText("4");
					ele = rootElement.addElement("return_msg");
					ele.setText(commResponse.getErrMsg());
					
				}
				
			}
			else
			{
				Element ele = rootElement.addElement("return_code");
				ele.setText("3");
				ele = rootElement.addElement("return_msg");
				ele.setText("没有对应的订单");
			}
			

		}

		String xmlString = rootElement.asXML();
		out.print(xmlString);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/synOrderStatus.do", method = RequestMethod.POST)
	public void synOrderStatus(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		response.setContentType("text/xml; charset=UTF-8");
		Document document = (Document) DocumentHelper.createDocument();
		Element rootElement = DocumentHelper.createElement("syn_order_status_response");
		document.setRootElement(rootElement);

		String userid = request.getParameter("userid");
		String appkey = request.getParameter("appkey");
		String method = request.getParameter("method");
		String format = request.getParameter("format");
		String timestamp = request.getParameter("timestamp");

		String tid = request.getParameter("tid");
		String statuscode = request.getParameter("statuscode");

		String sign = request.getParameter("sign");
		String signDe = WSCUtil.getMd5Code(userid + appkey + method + format + timestamp);
		if (!signDe.equals(sign)) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("1");
			ele = rootElement.addElement("return_msg");
			ele.setText("签名不对"+ signDe);
		} else if (Math.abs(DateUtil.getMillis(new Date()) - DateUtil.getMillis(DateUtil.getDate(timestamp)))
				/ (1000 * 60 * 60) > 30) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("2");
			ele = rootElement.addElement("return_msg");
			ele.setText("时间戳失效");
		} else {
			// 开始同步订单状态
			SalesOrderVo salesOrderVo = new SalesOrderVo();
			List<SalesOrderVo> salesOrderList = new ArrayList<SalesOrderVo>();
			salesOrderList = salesOrderService.querySalesOrderbyTid(tid);
			if (salesOrderList != null && salesOrderList.size() == 1) {
				salesOrderVo = salesOrderList.get(0);
				// 暂时放到备注
				salesOrderVo.setSellerMemo(salesOrderVo.getSellerMemo() + " " + statuscode);
				salesOrderService.updateSalesOrder(salesOrderVo);
			}

			Element suc_ele = rootElement.addElement("return_code");
			suc_ele.setText("0");
			Element msg_ele = rootElement.addElement("return_msg");
			msg_ele.setText("订单状态修改成功");
			Element item = rootElement.addElement("item");
			item.addElement("tid").addText(tid);
			item.addElement("statuscode").addText(statuscode);
			item.addElement("statusname").addText("");

		}

		String xmlString = rootElement.asXML();
		out.print(xmlString);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/synGoodsQty.do", method = RequestMethod.POST)
	public void searchTest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml; charset=UTF-8");

		Document document = (Document) DocumentHelper.createDocument();
		Element rootElement = DocumentHelper.createElement("goods_response");
		document.setRootElement(rootElement);

		String userid = request.getParameter("userid");
		String appkey = request.getParameter("appkey");
		String method = request.getParameter("method");
		String format = request.getParameter("format");
		String timestamp = request.getParameter("timestamp");

		String skucode = request.getParameter("skucode");
		String qty = request.getParameter("qty");

		String sign = request.getParameter("sign");
		String signDe = WSCUtil.getMd5Code(userid + appkey + method + format + timestamp);
		
		Long gdsQty = 0L;
		Long getQty = 0L;
		if (!signDe.equals(sign)) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("1");
			ele = rootElement.addElement("return_msg");
			ele.setText("签名不对"+ signDe);
		}
		else if (!qty.matches("[0-9]+"))
		{
			Element ele = rootElement.addElement("return_code");
			ele.setText("6");
			ele = rootElement.addElement("return_msg");
			ele.setText("商品数量格式不对");
		}
		else if (Math.abs(DateUtil.getMillis(new Date()) - DateUtil.getMillis(DateUtil.getDate(timestamp)))
				/ (1000 * 60 * 60) > 30) {
			Element ele = rootElement.addElement("return_code");
			ele.setText("2");
			ele = rootElement.addElement("return_msg");
			ele.setText("时间戳失效");
		} else {
			getQty = Long.parseLong(qty);
			// 检查skucode是否存在
			GdsInfoVo queryGds = new GdsInfoVo();
			GdsInfo gdsInfo = new GdsInfo();
			queryGds.setSkuOuterId(skucode);
			List<GdsInfo> gdsInfoList = gdsDao.selectGdsInfoGridListNoPage(queryGds);
			if (null != gdsInfoList && gdsInfoList.size() > 0) {
				gdsInfo = gdsInfoList.get(0);
				// 开始同步库存
				//获取WSC的仓库
				ShopVo shopVo = new ShopVo();
				shopVo.setPlanType("WSC");
				List<Shop> shopList = shopService.getShops(shopVo);
				
				@SuppressWarnings("unused")
				Long stockId = 0L ;
				if(null !=shopList)
				{
					stockId = shopList.get(0).getWarehouseId();
				}
				else
				{
					Element ele = rootElement.addElement("return_code");
					ele.setText("5");
					ele = rootElement.addElement("return_msg");
					ele.setText("没有微商城");
				}
				if(null== stockId || stockId==0L ){
					Element ele = rootElement.addElement("return_code");
					ele.setText("4");
					ele = rootElement.addElement("return_msg");
					ele.setText("店铺仓库没有定义");
				}
				else //正确
				{ //查找第一个商品库位
					LocStockVo locStockVo = new LocStockVo();
					locStockVo.setGdsId(gdsInfo.getId());
					locStockVo.setStockId(stockId);
					List<LocStockVo> locStockVoList  = locStockDao.selectByStatement(locStockVo);
					if(null==locStockVoList )
					{
						Element ele = rootElement.addElement("return_code");
						ele.setText("6");
						ele = rootElement.addElement("return_msg");
						ele.setText("商品库存不存在");
					}
					else
					{
						locStockVo = locStockVoList.get(0);
						locStockVo.setTotalQty(getQty);
						locStockService.setLocStock(locStockVo);
					}
				}
				//locStockService.synGoodsQty(locStockVo);
			} else {
				Element ele = rootElement.addElement("return_code");
				ele.setText("3");
				ele = rootElement.addElement("return_msg");
				ele.setText("商品不存在");
			}
		}

		PrintWriter out = response.getWriter();
		out.print(document.asXML());
		out.flush();
		out.close();
	}

}
