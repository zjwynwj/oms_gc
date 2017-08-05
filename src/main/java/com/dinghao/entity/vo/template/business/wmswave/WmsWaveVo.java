package com.dinghao.entity.vo.template.business.wmswave;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dinghao.entity.vo.manage.PageVo;

public class WmsWaveVo extends PageVo<WmsWaveVo>{
    /**
	  * @Fields serialVersionUID : TODO（拣货单请求类）
	  */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String waveNo;
    
    private Short ispickprint;

    private Short isscaned;

    private String memo;

    private Long createBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date createDate;

    private Long modifyBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date modifyDate;
    
    private String orderIds;
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date endTime;

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaveNo() {
        return waveNo;
    }

    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo == null ? null : waveNo.trim();
    }

    public Short getIspickprint() {
        return ispickprint;
    }

    public void setIspickprint(Short ispickprint) {
        this.ispickprint = ispickprint;
    }

    public Short getIsscaned() {
        return isscaned;
    }

    public void setIsscaned(Short isscaned) {
        this.isscaned = isscaned;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}