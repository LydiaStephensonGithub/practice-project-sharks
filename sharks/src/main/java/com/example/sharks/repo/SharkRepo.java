package com.example.sharks.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sharks.entity.Shark;

@Repository
public interface SharkRepo extends JpaRepository<Shark, Long> {
	
	//derived query
	List<Shark> findSharkBySpecies(String species);
	
	//SQL query
	@Query(value = "SELECT * from Shark WHERE habitat = ?1", nativeQuery = true)
	List<Shark> findSharkByHabitatSQL(String habitat);
	
	@Query("SELECT p from Shark p WHERE p.conservationStatus = ?1")
	List<Shark> findSharkByConservationStatusJPQL(String conservationStatus);
}
