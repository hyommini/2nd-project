package com.helloworld.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentsVO {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
	private int c_idx;
	private int g_idx;
	private int d_idx;
	private int b_idx;
	private int p_idx;
	private String nickname;
	private Date regdate;
	private String content;
	
	
	public int getP_idx() {
		return p_idx;
	}
	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}
	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}
	public int getG_idx() {
		return g_idx;
	}
	public void setG_idx(int g_idx) {
		this.g_idx = g_idx;
	}
	public int getD_idx() {
		return d_idx;
	}
	public void setD_idx(int d_idx) {
		this.d_idx = d_idx;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegdate() {
		return sdf.format(regdate);
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public CommentsVO(int c_idx, int g_idx, int d_idx, int b_idx, int p_idx, String nickname, Date regdate, String content) {
		super();
		this.c_idx = c_idx;
		this.g_idx = g_idx;
		this.d_idx = d_idx;
		this.b_idx = b_idx;
		this.p_idx = p_idx;
		this.nickname = nickname;
		this.regdate = regdate;
		this.content = content;
	}
	
	public CommentsVO(int c_idx, int g_idx, int d_idx, int b_idx, String nickname,
			String content) {
		this.c_idx = c_idx;
		this.g_idx = g_idx;
		this.d_idx = d_idx;
		this.b_idx = b_idx;
		this.nickname = nickname;
		this.content = content;
	}

	public CommentsVO() {}

	@Override
	public String toString() {
		return "CommentsVO [c_idx=" + c_idx + ", g_idx=" + g_idx + ", d_idx=" + d_idx + ", b_idx=" + b_idx
				+ ", nickname=" + nickname + ", regdate=" + regdate + ", content=" + content + "]";
	}
	
	
}
