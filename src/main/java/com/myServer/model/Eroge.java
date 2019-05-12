package com.myServer.model;

public class Eroge extends Goods {

	private String gid;
	private String[] illust;	
	
	public String[] getIllust() {
		return illust;
	}
	public void setIllust(String[] illust) {
		this.illust = illust;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
}