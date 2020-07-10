package com.javaex.vo;

public class ReplyBoardVo {
	private int no;
	private int user_no;
	private String name;
	private String title;
	private String content;
	private int hit;
	private String reg_date;
	private int group_no;
	private int order_no;
	private int depth;
	
	public ReplyBoardVo() {}

	public ReplyBoardVo(int no, int user_no, String name, String title, String content, int hit, String reg_date,
			int group_no, int order_no, int depth) {
		this.no = no;
		this.user_no = user_no;
		this.name = name;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "ReplyBoardVo [no=" + no + ", user_no=" + user_no + ", name=" + name + ", title=" + title + ", content="
				+ content + ", hit=" + hit + ", reg_date=" + reg_date + ", group_no=" + group_no + ", order_no="
				+ order_no + ", depth=" + depth + "]";
	}

}
