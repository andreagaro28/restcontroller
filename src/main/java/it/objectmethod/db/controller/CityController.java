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

import it.objectmethod.db.dao.CityDao;
import it.objectmethod.db.models.City;

@RestController
public class CityController {
	@Autowired
	private CityDao cityDao;
	
	
	@GetMapping("/city")
	public  ResponseEntity<City> city(@RequestParam(name="cityName", required = false) String cityName) throws SQLException {
		City city = null;
		ResponseEntity<City> resp = null;
		if(cityName != null) {
			city = cityDao.getCityByName(cityName);
			if(city != null) {
				resp = new ResponseEntity<City>(city, HttpStatus.OK);

			}else {
				resp = new ResponseEntity<City>(HttpStatus.NO_CONTENT);
			}
			
		}else {
			resp = new ResponseEntity<City>(HttpStatus.BAD_REQUEST);
		}
		
		return resp;
	}
	
	@GetMapping("/cityByCountry")
	public ResponseEntity<List<City>> cityByCountry(@RequestParam("countryCode") String countryCode) {
		List<City> cityList = new ArrayList<>();	
		ResponseEntity<List<City>> resp = null;
		if(countryCode != null) {
			try {
				cityList = cityDao.getCityByCode(countryCode);
				if(cityList.isEmpty()) {
					resp = new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
				}else {
					resp = new ResponseEntity<List<City>>(cityList, HttpStatus.OK);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			resp = new ResponseEntity<List<City>>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
}
