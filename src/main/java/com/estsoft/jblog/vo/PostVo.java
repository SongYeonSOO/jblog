package com.estsoft.jblog.vo;

public class PostVo {
	private Long post_no; // 포스트된 글번호
	private Long category_no; // 카테고리 번호
	private String title; // 글제목
	private String content; // 글내용
	private String reg_date; // post된 시간

	public Long getPost_no() {
		return post_no;
	}

	public void setPost_no(Long post_no) {
		this.post_no = post_no;
	}

	public Long getCategory_no() {
		return category_no;
	}

	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "PostVo [post_no=" + post_no + ", category_no=" + category_no + ", title=" + title + ", content="
				+ content + ", reg_date=" + reg_date + "]";
	}

}
