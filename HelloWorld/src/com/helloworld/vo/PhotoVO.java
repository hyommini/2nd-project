package com.helloworld.vo;

import java.util.Date;

public class PhotoVO {
	private int p_idx;
	private int u_idx;
	private String title;
	private String content;
	private String orifilename;
	private String filesysname;
	private String regdate;
	private String modidate;
//	private Date regdate;
//	private Date modidate;
	
	public int getP_idx() {
		return p_idx;
	}
	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
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
		return regdate;
}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getModidate() {
		return modidate;
	}
	public void setModidate(String modidate) {
		this.modidate = modidate;
	}
	public String getOrifilename() {
		return orifilename;
	}
	public void setOrifilename(String orifilename) {
		this.orifilename = orifilename;
	}
	public String getFilesysname() {
		return filesysname;
	}
	public void setFilesysname(String filesysname) {
		this.filesysname = filesysname;
	}
	@Override
	public String toString() {
		return "PhotoVO [p_idx=" + p_idx + ", u_idx=" + u_idx + ", title=" + title + ", content=" + content
				+ ", orifilename=" + orifilename + ", filesysname=" + filesysname + ", regdate=" + regdate
				+ ", modidate=" + modidate + "]";
	}
}
