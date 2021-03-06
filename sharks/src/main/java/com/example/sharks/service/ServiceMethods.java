package com.example.sharks.service;

import java.util.List;

public interface ServiceMethods<T> {
	
	//create
	T create(T t);
	
	//update
	T update(long id, T t);
	
	//delete
	boolean delete(long id);
	
	//read by id
	T readById(long id);
	
	//read all
	List<T> readAll();
	
	List<T> findSharkBySpecies(String species);
	
	List<T> findSharkByHabitatSQL(String habitat);

	List<T> findSharkByConservationStatusJPQL(String conservationStatus);
}
