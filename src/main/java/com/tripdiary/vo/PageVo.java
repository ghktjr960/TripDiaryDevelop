package com.tripdiary.vo;


// 나중에 페이징처리할 때 이 클래스 사용
public class PageVo {
	private String sort;
	private String place;
	private String tag;
	
	public PageVo() {
		// TODO Auto-generated constructor stub
	}
	
	public PageVo(String sort, String place, String tag) {
		this.sort = sort;
		this.place = place;
		this.tag = tag;
	}

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "PageVo [sort=" + sort + ", place=" + place + ", tag=" + tag + "]";
	}
	
	
	
}
