package com.javaex.vo;

public class PagingVO {
	private int startNum;
	private int endNum;
	private String keyword;
	
	public PagingVO() {}
	
	public PagingVO(int startNum, int endNum, String keyword) {
		this.startNum = startNum;
		this.endNum = endNum;
		this.keyword = keyword;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "PagingVO [startNum=" + startNum + ", endNum=" + endNum + ", keyword=" + keyword + "]";
	}
	
}
