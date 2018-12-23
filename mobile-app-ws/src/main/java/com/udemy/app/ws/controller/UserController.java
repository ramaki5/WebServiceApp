package com.udemy.app.ws.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.app.ws.model.UserDetails;
import com.udemy.app.ws.model.UserRest;

@RestController
@RequestMapping("/users")//http://localhost:8080/users
public class UserController {

	Map<String, UserRest> users;
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="limit", defaultValue="80") int limit, @RequestParam(value="sort") String sort) {
		
		return "Get User was called page = " + page + " and limit = " + limit + " Sort The Values : " +sort ;
	}
	
	@GetMapping(path="/{userId}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		if(users.containsKey(userId))
		{
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping(
			consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			, produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetails userDetails) {
		UserRest userRest = new UserRest();
		userRest.setEmail(userDetails.getEmail());
		userRest.setFirstName(userDetails.getFirstName());
		userRest.setLastName(userDetails.getLastName());
		String userId = UUID.randomUUID().toString();
		if(users==null) users=new HashMap<>();
		users.put(userId, userRest);
		return new ResponseEntity<UserRest>(HttpStatus.OK);
	}
	
	
	@PutMapping(path = "/{userId}" , consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			, produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public String updateUser(@PathVariable String userId, @Valid @RequestBody UserDetails userDetails) {
		
		return "Update User was called !!!";
	}
	
	@DeleteMapping(path="/{id}")
	public String DeleteUser() {
		
		return "Delete User was callede !!!";
	}
}
