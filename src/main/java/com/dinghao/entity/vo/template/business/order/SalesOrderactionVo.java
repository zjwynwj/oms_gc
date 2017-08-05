package com.dinghao.entity.vo.template.business.order;

import java.util.Date;

import com.dinghao.entity.vo.manage.PageVo;

public class SalesOrderactionVo extends PageVo<SalesOrderactionVo>{
    /**
	  * @Fields serialVersionUID : TODO（订单操作日志请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long orderId;

    private String actionType;

    private String actionMemo;

    private String actionBy;

    private Date actionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    public String getActionMemo() {
        return actionMemo;
    }

    public void setActionMemo(String actionMemo) {
        this.actionMemo = actionMemo == null ? null : actionMemo.trim();
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy == null ? null : actionBy.trim();
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}