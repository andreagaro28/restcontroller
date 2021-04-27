package it.objectmethod.db.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("userName") String userName) {
		ResponseEntity<String> resp = null;
		if(userName != null) {
			resp = new ResponseEntity<>(userName, HttpStatus.OK);
		}else {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}


}
