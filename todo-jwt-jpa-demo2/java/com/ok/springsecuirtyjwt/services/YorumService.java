package com.ok.springsecuirtyjwt.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ok.springsecuirtyjwt.models.Kullanici;
import com.ok.springsecuirtyjwt.models.Urunler;
import com.ok.springsecuirtyjwt.models.Yorum;
import com.ok.springsecuirtyjwt.util.JwtUtil;

@Service
public class YorumService {

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private KullaniciService kullaniciService;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	EntityManager em = emf.createEntityManager();

	public List<Yorum> YorumGoster(int urunId) {
		List<Yorum> yorumListe = new ArrayList<Yorum>();
		yorumListe = em.createQuery("SELECT c FROM Yorum c where urunId = :urunId").setParameter("urunId", urunId)
				.getResultList();
		return yorumListe;
	}

	public Yorum BirYorum(int yorumId) {
		Yorum yorum = em.find(Yorum.class, yorumId);
		return yorum;
	}

	public Yorum YorumSil(int yorumId, String username) {
		Yorum yorum = em.find(Yorum.class, yorumId);
		if(yorum == null)
			return null;
		if (yorum.getKullanici().getAd().equals(username)) {
			em.getTransaction().begin();
			em.remove(yorum);
			em.getTransaction().commit();
			return yorum;
		}
		return null;
	}

	public Yorum YorumEkle(int urunId, String username, String yorum) {
		Urunler urun = em.find(Urunler.class, urunId);
		Yorum yeniYorum = new Yorum();
		yeniYorum.setKullanici(kullaniciService.getKullanici(username));
		yeniYorum.setUrunler(urun);
		yeniYorum.setYorum(yorum);

		em.getTransaction().begin();
		em.persist(yeniYorum);
		em.getTransaction().commit();

		return yeniYorum;
	}
	
	
	public Yorum YorumGuncelle(int yorumId, String yorum, String username) {
		Yorum yorumObject = em.find(Yorum.class, yorumId);
		System.out.println(yorumObject.getKullanici().getAd());
		System.out.println(username);
		if(yorumObject.getKullanici().getAd().equals(username)) {
			yorumObject.setYorum(yorum);
			em.getTransaction().begin();
			em.merge(yorumObject);
			em.getTransaction().commit();
			return yorumObject;
		}
		return null;
	}

}
