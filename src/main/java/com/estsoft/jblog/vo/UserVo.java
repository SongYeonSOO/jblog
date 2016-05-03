package com.estsoft.jblog.vo;

public class UserVo {
	private String id;
	private String name;
	private String passwd;
	private String reg_date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", passwd=" + passwd + ", reg_date=" + reg_date + "]";
	}

}
