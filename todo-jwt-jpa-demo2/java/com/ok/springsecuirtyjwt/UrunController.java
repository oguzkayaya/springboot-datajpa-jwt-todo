package com.ok.springsecuirtyjwt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ok.springsecuirtyjwt.models.Urunler;
import com.ok.springsecuirtyjwt.services.UrunService;

@RestController
public class UrunController {

	@Autowired
	UrunService urunService;

	@RequestMapping(value = "/urunler", method = RequestMethod.GET)
	public List<Urunler> UrunleriGetir() {
		return urunService.TumUrunler();
	}

	@RequestMapping(value = "/urunler/{id}", method = RequestMethod.GET)
	public Urunler UrunGetir(@PathVariable(value = "id") int id) {
		return urunService.BirUrun(id);
	}

	@RequestMapping(value = "/urunler/{id}", method = RequestMethod.DELETE)
	public Urunler UrunSil(@PathVariable(value = "id") int id) {
		return urunService.UrunSil(id);
	}

	@RequestMapping(value = "/urunler", method = RequestMethod.POST)
	public Urunler UrunEkle(@RequestBody Urunler yeni) {
		return urunService.UrunEkle(yeni);
	}

	@RequestMapping(value = "/urunler/{id}", method = RequestMethod.PUT)
	public Urunler UrunGuncelle(@RequestBody Urunler yeni, @PathVariable(value = "id") int id) {
		return urunService.UrunGuncelle(yeni, id);
	}

}
