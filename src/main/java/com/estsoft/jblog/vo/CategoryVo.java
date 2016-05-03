package com.estsoft.jblog.vo;

public class CategoryVo {
	private Long category_no; // 카테고리번호
	private Long no;			// 블로그번호
	private String name;		//카테고리이름
	private String description;	//카테고리 설명
	private String reg_date;	//카테고리 생성일?
	private Long post_count;	//카테고리에 post된 글 수
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getPost_count() {
		return post_count;
	}
	public void setPost_count(Long post_count) {
		this.post_count = post_count;
	}
	@Override
	public String toString() {
		return "CategoryVo [category_no=" + category_no + ", no=" + no + ", name=" + name + ", description="
				+ description + ", reg_date=" + reg_date + ", post_count=" + post_count + "]";
	}
	
}
