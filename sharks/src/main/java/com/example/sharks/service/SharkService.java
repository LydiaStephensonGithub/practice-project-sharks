package com.example.sharks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sharks.entity.Shark;
import com.example.sharks.exception.SharkNotFoundException;
import com.example.sharks.repo.SharkRepo;

@Service
public class SharkService implements ServiceMethods<Shark> {
	
	private SharkRepo repo;
	
	public SharkService(SharkRepo repo) {
		this.repo = repo;
	}

	@Override
	public Shark create(Shark t) {
		return this.repo.save(t);
	}

	@Override
	public Shark update(long id, Shark t) {
		Optional<Shark> existingEntry = this.repo.findById(id);
		if (existingEntry.isPresent()) {
			Shark entry = existingEntry.get();
			entry.setSpecies(t.getSpecies());
			entry.setLatinName(t.getLatinName());
			entry.setHabitat(t.getHabitat());
			entry.setConservationStatus(t.getConservationStatus());
			
			return this.repo.saveAndFlush(entry);
		} 
		throw new SharkNotFoundException();
	}

	@Override
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);	
	}

	@Override
	public Shark readById(long id) {
		Shark found = this.repo.findById(id).orElseThrow(SharkNotFoundException::new);
		return found;
	}

	@Override
	public List<Shark> readAll() {
		return this.repo.findAll();
	}

	@Override
	public List<Shark> findSharkBySpecies(String species) {
		return this.repo.findSharkBySpecies(species);
	}

	@Override
	public List<Shark> findSharkByHabitatSQL(String habitat) {
		return this.repo.findSharkByHabitatSQL(habitat);
	}

	@Override
	public List<Shark> findSharkByConservationStatusJPQL(String conservationStatus) {
		return this.repo.findSharkByConservationStatusJPQL(conservationStatus);
	}
	
}
