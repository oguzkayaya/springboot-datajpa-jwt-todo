package com.ok.springsecuirtyjwt.services;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ok.springsecuirtyjwt.models.Kullanici;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();

		java.util.List<Kullanici> kullanici =
				em.createQuery("SELECT c FROM Kullanici c WHERE c.ad LIKE :ad").setParameter("ad", username)
						.setMaxResults(1).getResultList();

		return new User(kullanici.iterator().next().getAd(), kullanici.iterator().next().getSifre(), new ArrayList<>());
	}
}
