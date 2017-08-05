package com.dinghao.entity.htobject;



public class requestSearch extends requestEntity 
{
	private String startTime;//格式：yyyy-MM-dd HH:mm:ss 开始时间
	private String endTime; //格式：yyyy-MM-dd HH:mm:ss 结束时间(开始时间和结束时间不得大于一个月)
	private String searchType;//查询类型：crt按照订单创建时间，upd按照订单修改时间
	private String pageNo;//起始页
	private String pageSize;//当页显示多少条数据(1~20)
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
}
