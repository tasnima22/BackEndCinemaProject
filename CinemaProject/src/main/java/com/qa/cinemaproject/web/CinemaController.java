package com.qa.cinemaproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cinemaproject.domain.Cinema;
import com.qa.cinemaproject.service.CinemaService;

@RestController //Informs Spring that this is a controller
public class CinemaController {

	private CinemaService service;
	
	@Autowired
	public CinemaController(CinemaService service) {
		super();
		this.service=service;
	}
	
	@PostMapping("/create") // @RequestBody pulls the parameter from the body of the request

	public ResponseEntity<Cinema> createCinema(@RequestBody Cinema c) {
		Cinema created = this.service.create(c);
		ResponseEntity<Cinema> response = new ResponseEntity<Cinema>(created, HttpStatus.CREATED); // 201 - created
		return response;
	}
	
	@GetMapping("/getAll") 
	public ResponseEntity<List<Cinema>> getAllCinema() {
		return ResponseEntity.ok(this.service.getAll()); //200 - ok
	}
	
	@GetMapping("/get/{id}")  // 200 ok
	public Cinema getCinemaID(@PathVariable Integer id) {
		return this.service.getOne(id);
	}
	@PutMapping("/replace/{id}") // 202 accepted
	public ResponseEntity<Cinema> replaceCinema(@PathVariable Integer id, @RequestBody Cinema newCinema) {
		Cinema body = this.service.replace(id, newCinema);
		ResponseEntity<Cinema> response = new ResponseEntity<Cinema>(body, HttpStatus.ACCEPTED);
		return response;
	}
	@DeleteMapping("/remove/{id}") // 204 no content 
	public ResponseEntity<?> removeCinemaID(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getByCinema/{cinema}") //200 ok
	public ResponseEntity<List<Cinema>> getCinemaByCinema(@PathVariable String cinema) {
	List<Cinema> found = this.service.getAllbyCinema(cinema);
	return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByFilm/{film}")
	public ResponseEntity<List<Cinema>> getCinemaByFilm(@PathVariable String film) {
		List<Cinema> found = this.service.getByFilm(film);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByCost/{cost}")
	public ResponseEntity<List<Cinema>> getCinemaByCost(@PathVariable Double cost) {
		List<Cinema> found = this.service.getCinemaByCost(cost);
		return ResponseEntity.ok(found);
	}
}
