package com.helloworld.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
	private int b_idx;
	private int u_idx;
	private String title;
	private String content;
	private Date regdate;
	private String nickname;
	private int hit = 0;
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
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
	public String getRegdate() {
		return sdf.format(regdate);
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
		
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public BoardVO(int b_idx, int u_idx, String title, String content, Date regdate, String nickname, int hit) {
		super();
		this.b_idx = b_idx;
		this.u_idx = u_idx;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.nickname = nickname;
		this.hit = hit;
	}
	
	public BoardVO(int b_idx, String title) {
		this.b_idx = b_idx;
		this.title = title;
	}
	public BoardVO() {}
	@Override
	public String toString() {
		return "BoardVO [b_idx=" + b_idx + ", u_idx=" + u_idx + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", nickname=" + nickname + ", hit=" + hit + "]";
	}
	
	
}
