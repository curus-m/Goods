package com.myServer.model;

public class Eroge extends Goods {

	private String id;	
	private String[] illust;	
	private String image;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
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
}