package com.ok.springsecuirtyjwt.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import com.ok.springsecuirtyjwt.models.Urunler;

@Service
public class UrunlerimService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	EntityManager em = emf.createEntityManager();
	
	public List<Urunler> TumUrunlerim() {
		List<Urunler> urunListe = new ArrayList<Urunler>();
		urunListe = 
		em.createQuery("SELECT c FROM Urunler c").getResultList();
		return urunListe;
	}
	
	
	public Urunler BirUrun(int id) {
		return em.find(Urunler.class, id);
	}
	
	public Urunler UrunSil(int id) {
		Urunler sil = BirUrun(id);
		em.getTransaction().begin();
		em.remove(sil);
		em.getTransaction().commit();
		return sil;
	}
	
	public Urunler UrunEkle(Urunler yeni) {
		em.getTransaction().begin();
		em.persist(yeni);
		em.getTransaction().commit();
		return yeni;
	}
	
	public Urunler UrunGuncelle(Urunler yeni, int id) {
		
		Urunler eski = em.find(Urunler.class, id);
		eski = yeni;
		eski.setId(id);
		
		em.getTransaction().begin();
		em.merge(eski);
		em.getTransaction().commit();
		
		return eski;
	}
}
