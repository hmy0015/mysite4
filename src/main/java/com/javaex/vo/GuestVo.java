package com.javaex.vo;

public class GuestVo {
	private int no;
	private String name, pw, content, date;

	public GuestVo() {
	}

	public GuestVo(String name, String pw, String content) {
		this.name = name;
		this.pw = pw;
		this.content = content;
	}

	public GuestVo(int no, String name, String content, String date) {
		this.no = no;
		this.name = name;
		this.content = content;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", content=" + content + ", date=" + date + "]";
	}
}
