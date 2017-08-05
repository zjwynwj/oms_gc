/*
* @ClassName:WmsStockLog.java
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: 
*-----------Zihan--鼎好科技 版权所有------------
* @date 2016-02-29
*/
package com.dinghao.entity.template.wmswtocklog;

import org.apache.commons.lang.StringUtils;

import com.dinghao.entity.manage.BaseEntity;

public class WmsStockLog extends BaseEntity {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	

    /**
     * .仓库编号
     */
    private Integer stockId;

    /**
     * .库位
     */
    private Integer locId;

    /**
     * .商品号
     */
    private Integer gdsId;

    /**
     * .原始库存
     */
    private Long oldqty;

    /**
     * .新增库存
     */
    private Long addqty;

    /**
     * .实际库存
     */
    private Long newqty;

    /**
     * .移动原因
     */
    private String type;

    /**
     * .
     */
    private String fromNo;

    

    

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    public Integer getGdsId() {
        return gdsId;
    }

    public void setGdsId(Integer gdsId) {
        this.gdsId = gdsId;
    }

    public Long getOldqty() {
        return oldqty;
    }

    public void setOldqty(Long oldqty) {
        this.oldqty = oldqty;
    }

    public Long getAddqty() {
        return addqty;
    }

    public void setAddqty(Long addqty) {
        this.addqty = addqty;
    }

    public Long getNewqty() {
        return newqty;
    }

    public void setNewqty(Long newqty) {
        this.newqty = newqty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = StringUtils.isBlank(type)? null : type.trim();
    }

    public String getFromNo() {
        return fromNo;
    }

    public void setFromNo(String fromNo) {
        this.fromNo = StringUtils.isBlank(fromNo)? null : fromNo.trim();
    }  
}