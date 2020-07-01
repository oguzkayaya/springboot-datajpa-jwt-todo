package com.ok.springsecuirtyjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "yorum")
public class Yorum {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "kullaniciId", referencedColumnName = "id")
	private Kullanici kullanici;
	private String yorum;
	@ManyToOne
	@JoinColumn(name = "urunId", referencedColumnName = "id")
	private Urunler urunler;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Kullanici getKullanici() {
		return kullanici;
	}
	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}
	public String getYorum() {
		return yorum;
	}
	public void setYorum(String yorum) {
		this.yorum = yorum;
	}
	public Urunler getUrunler() {
		return urunler;
	}
	public void setUrunler(Urunler urunler) {
		this.urunler = urunler;
	}

	
	

}
