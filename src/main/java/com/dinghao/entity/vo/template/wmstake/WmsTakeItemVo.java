/*
* @ClassName:WmsTakeItem.java
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: 
*-----------Zihan--鼎好科技 版权所有------------
* @date 2016-01-22
*/
package com.dinghao.entity.vo.template.wmstake;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.dinghao.entity.vo.manage.PageVo;

public class WmsTakeItemVo extends PageVo<WmsTakeItemVo>{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	/**
     * .明细ID
     */
    private Long id;

    /**
     * .商品ID
     */
    private Long gdsId;

    /**
     * .库位ID
     */
    private Long locId;

    /**
     * .盘点单ID
     */
    private Long takeId;

    /**
     * .系统库存
     */
    private Integer sysQty;

    /**
     * .盘点库存
     */
    private Integer countQty;

    /**
     * .盘点差异
     */
    private Integer diffQty;

    
    private Integer  scanQty;
    
    
	public Integer getScanQty() {
		return scanQty;
	}

	public void setScanQty(Integer scanQty) {
		this.scanQty = scanQty;
	}

	private String gdsPact;
    /**
     * .备注
     */
    private String memo;

    /**
     * .是否取消档案 0取消归档 ;1归档
     */
    private Boolean isDeleted;

    /**
     * .备用
     */
    private String remarks;

    /**
     * .备用1
     */
    private String remarks1;

    /**
     * .备用2
     */
    private String remarks2;

    /**
     * .创建时间
     */
    private Date createDate;

    /**
     * .修改时间
     */
    private Date modifyDate;

    /**
     * .修改者
     */
    private Long modifyBy;

    /**
     * .修改者
     */
    private Long createBy;
    /**
     * .是否锁定 0 未锁定;1锁定
     */
    private Boolean isLocked;

    public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Long getLockedPeople() {
		return lockedPeople;
	}

	public void setLockedPeople(Long lockedPeople) {
		this.lockedPeople = lockedPeople;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	/**
     * .锁定人
     */
    private Long lockedPeople;

    /**
     * .锁定时间
     */
    private Date lockedDate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    public Integer getSysQty() {
        return sysQty;
    }

    public void setSysQty(Integer sysQty) {
        this.sysQty = sysQty;
    }

    public Integer getCountQty() {
        return countQty;
    }

    public void setCountQty(Integer countQty) {
        this.countQty = countQty;
    }

    public Integer getDiffQty() {
        return diffQty;
    }

    public void setDiffQty(Integer diffQty) {
        this.diffQty = diffQty;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = StringUtils.isBlank(memo)? null : memo.trim();
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = StringUtils.isBlank(remarks)? null : remarks.trim();
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = StringUtils.isBlank(remarks1)? null : remarks1.trim();
    }

    public String getRemarks2() {
        return remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = StringUtils.isBlank(remarks2)? null : remarks2.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

	public String getGdsPact() {
		return gdsPact;
	}

	public void setGdsPact(String gdsPact) {
		this.gdsPact = gdsPact;
	}
    
}