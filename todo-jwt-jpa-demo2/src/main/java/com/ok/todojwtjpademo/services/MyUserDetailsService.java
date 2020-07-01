package com.ok.todojwtjpademo.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ok.todojwtjpademo.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {


	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {

		
		com.ok.todojwtjpademo.models.User myUser = userRepository.findByUsername(username);
		
		return new User(myUser.getUsername(), myUser.getPassword(), new ArrayList<>());
		
		
	}

}
