package com.estsoft.jblog.vo;

public class BlogVo {
	private Long no;		// 블로그번호
	private String title;	//블로그 이름
	private String logo;	// 로고 위치
	private String id;	// 회원id

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BlogVo [no=" + no + ", title=" + title + ", logo=" + logo + ", id=" + id + "]";
	}
}
