package com.myServer.model;

public class Eroge extends Goods {

	private String gid;
	private String[] illust;	
	private String image;
	
	public String[] getIllust() {
		return illust;
	}
	public void setIllust(String[] illust) {
		this.illust = illust;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
}