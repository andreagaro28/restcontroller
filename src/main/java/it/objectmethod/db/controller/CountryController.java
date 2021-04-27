package it.objectmethod.db.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.db.dao.CountryDao;
import it.objectmethod.db.models.Country;

@RestController
public class CountryController {

	@Autowired
	private CountryDao countryDao;

	@GetMapping("/country")
	public ResponseEntity<List<Country>> country(@RequestParam(name="name", required=false) String name, @RequestParam(name="continent", required=false) String continent){
		List<Country> list = new ArrayList<>();
		ResponseEntity<List<Country>> resp = null;

		if(name != null && continent != null) {
			try {
				list = countryDao.getCountryByContinentName(name, continent);
				if(list.isEmpty()) {
					resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}else {
					resp = new ResponseEntity<>(list, HttpStatus.OK);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return resp;
	}


	@GetMapping("/countryByContinent")
	public ResponseEntity<List<Country>> countryBycontinent(@RequestParam("continentName") String continent) {
		List<Country> countryList = new ArrayList<>();
		ResponseEntity<List<Country>> resp = null;
		
		if(continent != null) {
			try {
				countryList = countryDao.getCountryByContinentList(continent);
				
				if(countryList.isEmpty()) {
					resp = new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);

				}else {
					resp = new ResponseEntity<List<Country>>(countryList ,HttpStatus.OK);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			resp = new ResponseEntity<List<Country>>(HttpStatus.BAD_REQUEST);
		}	

		return resp;
	}
}
