package com.ok.springsecuirtyjwt.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ok.springsecuirtyjwt.models.Kullanici;
import com.ok.springsecuirtyjwt.util.JwtUtil;

@Service
public class KullaniciService {
	@Autowired
	private JwtUtil jwtUtil;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	EntityManager em = emf.createEntityManager();

	public Kullanici getLogonKullanici(HttpServletRequest request) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();

		final String authorizationHeader = request.getHeader("Authorization");
		final String username = jwtUtil.extractUsername(authorizationHeader.substring(7));
		java.util.List<Kullanici> kullanici = em.createQuery("SELECT c FROM Kullanici c WHERE c.ad LIKE :ad")
				.setParameter("ad", username).setMaxResults(1).getResultList();

		return kullanici.iterator().next();
	}

	public Kullanici getKullanici(String username) {
		List<Kullanici> kullanici = em.createQuery("SELECT c FROM Kullanici c WHERE c.ad LIKE :ad")
				.setParameter("ad", username).getResultList();
		return kullanici.get(0);
	}
}
