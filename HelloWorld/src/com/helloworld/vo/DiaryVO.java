package com.helloworld.vo;

import java.sql.Date;

public class DiaryVO {
	private int d_idx;
	private int u_idx;
	private String title;
	private String content;
	private Date regdate;
	private int hit;
	
	public int getD_idx() {
		return d_idx;
	}
	public void setD_idx(int d_idx) {
		this.d_idx = d_idx;
	}
	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	@Override
	public String toString() {
		return "DiaryVO [d_idx=" + d_idx + ", u_idx=" + u_idx + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", hit=" + hit + "]";
	}
	
}
