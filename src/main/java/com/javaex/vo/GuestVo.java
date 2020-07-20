package com.javaex.vo;

public class GuestVo {
	private int no;
	private String name, pw, content, reg_date;

	public GuestVo() {
	}

	public GuestVo(String name, String pw, String content) {
		this.name = name;
		this.pw = pw;
		this.content = content;
	}

	public GuestVo(int no, String name, String content, String reg_date) {
		this.no = no;
		this.name = name;
		this.content = content;
		this.reg_date = reg_date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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
		return "GuestVo [no=" + no + ", name=" + name + ", pw=" + pw + ", content=" + content + ", reg_date=" + reg_date
				+ "]";
	}


}
