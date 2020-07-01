package com.ok.springsecuirtyjwt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ok.springsecuirtyjwt.models.AuthenticationRequest;
import com.ok.springsecuirtyjwt.models.AuthenticationResponse;
import com.ok.springsecuirtyjwt.models.Kullanici;
import com.ok.springsecuirtyjwt.services.MyUserDetailsService;
import com.ok.springsecuirtyjwt.util.JwtUtil;

@RestController
public class HelloController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping("/hello")
	public String hello(HttpServletRequest request) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		final String authorizationHeader = request.getHeader("Authorization");
		final String username = jwtUtil.extractUsername(authorizationHeader.substring(7));
		java.util.List<Kullanici> kullanici =
				em.createQuery("SELECT c FROM Kullanici c WHERE c.ad LIKE :ad").setParameter("ad", username)
						.setMaxResults(1).getResultList();
		return "hello " + kullanici;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
