package it.objectmethod.db.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.db.dao.ContinentsDao;

@RestController
public class ContinentsController {
	
	@Autowired
	private ContinentsDao continentsDao;

	@GetMapping("/continents")
	public ResponseEntity<List<String>> continents() throws SQLException {
		ResponseEntity<List<String>> resp = null;
		
		List<String> continentsList = continentsDao.getContinents();
		
		if(continentsList.isEmpty()) {
			resp = new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}else {
			resp = new ResponseEntity<List<String>>(continentsList, HttpStatus.OK);

		}
		
		return resp;
	}
	
}
