package com.dinghao.constant;

/**
  * @ClassName: ExceptionConstant
  * @Description: TODO  异常信息常量
  * @author helong 
  * @date 2016年1月4日 上午9:39:52
  * @version V1.0
  *
 */
public class ExceptionConstant {
	/**
	 * 按照模块号定义异常的信息   规则为: ERR_DH{XX}{YYYY}  ERR_DH{模块号}{序号}   自己的模块在后面继续添加
	 *    系统：00
	 * 单据管理：01
	 * 商品管理：02
	 * 采购管理：03
	 * 仓库管理：04
	 * 订单管理:05
	 * 售后管理：06
	 * 店铺管理：07
	 * 基础管理：08
	 * 财务管理：09
	 * 客户管理：10
	 */
	/*1.系统异常*/
	public static final String ERR_DH000001 = "未知错误，请联系管理员!";
	
	public static final String ERR_DH000002 = "违法 的sessionkey!";
	
	public static final String ERR_DH000003 = "调用淘宝接口出错!";
	
	public static final String ERR_DH000004 = "订单下载失败!";
	
	public static final String ERR_DH000005 = "数据库存在多条相同订单!";
	
	/*2.单据管理*/
	public static final String ERR_DH010001 = "生成单据号失败,请联系管理员!";
	
	/*3.商品管理*/
	public static final String ERR_DH020001 = "该商品分类不存在!";
	
	public static final String ERR_DH020002 = "该商品分类存在子分类,不能被删除!";
	
	public static final String ERR_DH020003 = "该商品分类下存在商品,不能被删除!";
	
	public static final String ERR_DH020004 = "该商品属性名不存在!";
	
	public static final String ERR_DH020005 = "该商品属性值不存在!";
	
	public static final String ERR_DH020006 = "已存在相同商品编码规格代码组合,请联系管理员!";
	
	public static final String ERR_DH020007 = "商品不存在,请联系管理员!";
	
	public static final String ERR_DH020008 = "没有符合的商品!";

	public static final String ERR_DH020009 = "获取平台商品数据失败!";
	
	public static final String ERR_DH020010 = "转化商品数据异常!";
	
	public static final String ERR_DH020011 = "已存在相同的属性名!";
	
	public static final String ERR_DH020012 = "已存在相同商品条码,请修改!";
	
	/*4.采购管理*/
	public static final String ERR_DH030001 = "已存在相同的采购编码,请联系管理员!";
	
	public static final String ERR_DH030002 = "采购订单不存在,请联系管理员!";
	
	/*10.客户管理*/
	public static final String ERR_DH100001 = "已存在相同的客户编码,请联系管理员!";
	
	public static final String ERR_DH100002 = "此客户不存在,请联系管理员!";
	
	public static final String ERR_DH100003 = "此会员在该店铺内已存在!";
	public static final String ERR_DH100004 = "找不到改会员!";
	
	
}
