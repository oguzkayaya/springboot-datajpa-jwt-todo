package com.ok.springsecuirtyjwt.models;

public class YorumRequest {
	
	String yorum;
	
	public YorumRequest() {
		super();
	}

	public YorumRequest(String yorum) {
		super();
		this.yorum = yorum;
	}

	public String getYorum() {
		return yorum;
	}

	public void setYorum(String yorum) {
		this.yorum = yorum;
	}
	
}
