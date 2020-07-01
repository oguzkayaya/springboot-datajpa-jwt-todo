package com.ok.springsecuirtyjwt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ok.springsecuirtyjwt.models.Kullanici;
import com.ok.springsecuirtyjwt.models.Yorum;
import com.ok.springsecuirtyjwt.models.YorumRequest;
import com.ok.springsecuirtyjwt.services.YorumService;
import com.ok.springsecuirtyjwt.util.JwtUtil;

@RestController
public class YorumController {

	@Autowired
	YorumService yorumService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/urunler/{id}/yorum", method = RequestMethod.GET)
	public List<Yorum> YorumGetir(@PathVariable int id) {
		return yorumService.YorumGoster(id);
	}

	@RequestMapping(value = "/yorumlar/{id}", method = RequestMethod.GET)
	public Yorum BirYorum(@PathVariable int id, HttpServletRequest request) {
		return yorumService.BirYorum(id);
	}

	@RequestMapping(value = "/urunler/{id}/yorum", method = RequestMethod.POST)
	public Yorum YorumEkle(@PathVariable(name = "id") int urunId, HttpServletRequest request,
			@RequestBody YorumRequest yorum) {
		String username = jwtTokenUtil.getUsernameFromRequest(request);
		return yorumService.YorumEkle(urunId, username, yorum.getYorum());
	}

	@RequestMapping(value = "/yorumlar/{id}", method = RequestMethod.DELETE)
	public Yorum YorumSil(@PathVariable(name = "id") int id, HttpServletRequest request) {
		String username = jwtTokenUtil.getUsernameFromRequest(request);
		return yorumService.YorumSil(id, username);
	}

	@RequestMapping(value = "/yorumlar/{id}", method = RequestMethod.PUT)
	public Yorum YorumGuncelle(@PathVariable(name = "id") int id, @RequestBody YorumRequest yorum,
			HttpServletRequest request) {
		String username = jwtTokenUtil.getUsernameFromRequest(request);
		return yorumService.YorumGuncelle(id, yorum.getYorum(), username);

	}

}
