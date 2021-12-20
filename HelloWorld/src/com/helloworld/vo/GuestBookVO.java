package com.helloworld.vo;

public class GuestBookVO {
	private int g_idx;
	private int u_idx;
	private String content;
	private String regdate;
	private String nickname;

	public GuestBookVO(int u_idx, String content, String nickname) {
		super();
		this.u_idx = u_idx;
		this.content = content;
		this.nickname = nickname;
	}

	public int getG_idx() {
		return g_idx;
	}




	public void setG_idx(int g_idx) {
		this.g_idx = g_idx;
	}




	public int getU_idx() {
		return u_idx;
	}




	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getRegdate() {
		return regdate;
	}




	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}




	public String getNickname() {
		return nickname;
	}




	public void setNickname(String nickname) {
		this.nickname = nickname;
	}




	@Override
	public String toString() {
		return "GuestBookVO [g_idx=" + g_idx + ", u_idx=" + u_idx + ", content=" + content + ", regdate=" + regdate
				+ ", nickname=" + nickname + "]";
	}
}
