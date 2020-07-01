package com.ok.springsecuirtyjwt.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kullanici {

	@Id
	private int id;
	private String ad;
	private String sifre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	@Override
	public String toString() {
		return "Kullanici [id=" + id + ", ad=" + ad + ", sifre=" + sifre + "]";
	}
	
	
}
