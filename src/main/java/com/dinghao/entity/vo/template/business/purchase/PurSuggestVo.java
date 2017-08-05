package com.dinghao.entity.vo.template.business.purchase;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dinghao.entity.vo.manage.PageVo;

public class PurSuggestVo   extends PageVo<PurSuggestVo>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private Long id;

    private String suggestNo;

    public String getBusiDate1() {
		return busiDate1;
	}

	public void setBusiDate1(String busiDate1) {
		this.busiDate1 = busiDate1;
	}

	public String getBusiDate2() {
		return busiDate2;
	}

	public void setBusiDate2(String busiDate2) {
		this.busiDate2 = busiDate2;
	}

	private Date busiDate;
    
    private String busiDate1;
 	private String busiDate2;
 	private String userName;
 	   	
   	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private List<PurSuggestGoodsVo> purSuggestGoodsVoList;
    
   	public List<PurSuggestGoodsVo> getPurSuggestGoodsVoList() {
		return purSuggestGoodsVoList;
	}

	public void setPurSuggestGoodsVoList(List<PurSuggestGoodsVo> purSuggestGoodsVoList) {
		this.purSuggestGoodsVoList = purSuggestGoodsVoList;
	}

	private Date planDate;

 

	private String status;

    private String busiPerson;

    private BigDecimal amount;

    private Long createBy;

    private Date createDate;

    private Long modifyBy;

    private Date modifyDate;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuggestNo() {
        return suggestNo;
    }

    public void setSuggestNo(String suggestNo) {
        this.suggestNo = suggestNo == null ? null : suggestNo.trim();
    }

    public Date getBusiDate() {
        return busiDate;
    }

    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBusiPerson() {
        return busiPerson;
    }

    public void setBusiPerson(String busiPerson) {
        this.busiPerson = busiPerson == null ? null : busiPerson.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}