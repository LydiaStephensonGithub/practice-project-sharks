package com.example.sharks.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sharks.entity.Shark;
import com.example.sharks.service.SharkService;

@RestController
@RequestMapping("/shark")
public class SharkController {
	
	private SharkService service;
	
	private SharkController(SharkService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Shark> create(@RequestBody Shark shark) {
		return new ResponseEntity<Shark>(this.service.create(shark), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Shark> update(@PathVariable long id, @RequestBody Shark shark) {
		return new ResponseEntity<Shark>(this.service.update(id, shark), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/readById/{id}")
	public ResponseEntity<Shark> readById(@PathVariable long id) {
		return new ResponseEntity<Shark>(this.service.readById(id), HttpStatus.OK);
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Shark>> readAll() {
		return new ResponseEntity<List<Shark>>(this.service.readAll(), HttpStatus.OK);
	}
	
	@GetMapping("/findSharkBySpecies/{species}")
	public ResponseEntity<List<Shark>> findSharkBySpecies(@PathVariable String species) {
		return new ResponseEntity<List<Shark>>(this.service.findSharkBySpecies(species), HttpStatus.OK);
	}
	
	@GetMapping("/findSharkByHabitatSQL/{habitat}")
	public ResponseEntity<List<Shark>> findSharkByHabitatSQL(@PathVariable String habitat) {
		return new ResponseEntity<List<Shark>>(this.service.findSharkByHabitatSQL(habitat), HttpStatus.OK);
	}
	
	@GetMapping("/findSharkByConservationStatusJPQL/{conservationStatus}")
	public ResponseEntity<List<Shark>> findSharkByConservationStatusJPQL(@PathVariable String conservationStatus) {
		return new ResponseEntity<List<Shark>>(this.service.findSharkByConservationStatusJPQL(conservationStatus), HttpStatus.OK);
	}
	
}
