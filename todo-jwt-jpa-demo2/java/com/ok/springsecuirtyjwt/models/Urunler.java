package com.ok.springsecuirtyjwt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Urunler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String urunAd;
	private int urunAdet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrunAd() {
		return urunAd;
	}

	public void setUrunAd(String urunAd) {
		this.urunAd = urunAd;
	}

	public int getUrunAdet() {
		return urunAdet;
	}

	public void setUrunAdet(int urunAdet) {
		this.urunAdet = urunAdet;
	}

	@Override
	public String toString() {
		return "Urun [id=" + id + ", UrunAd=" + urunAd + ", UrunAdet=" + urunAdet + "]";
	}

}
