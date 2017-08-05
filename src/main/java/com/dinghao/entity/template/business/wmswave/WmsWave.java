package com.dinghao.entity.template.business.wmswave;

import com.dinghao.entity.manage.BaseEntity;

public class WmsWave extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（拣货单响应类）
	  */
	private static final long serialVersionUID = 1L;

    private String waveNo;

    private Short ispickprint;

    private Short isscaned;

    private String memo;

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

}