package com.example.sharks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.sharks.entity.Shark;
import com.example.sharks.repo.SharkRepo;

@ActiveProfiles("test")
@SpringBootTest
public class SharkServiceUnitTesting {

	@Autowired
	private SharkService service;
	
	@MockBean
	private SharkRepo repo;
	
	@Test 
	public void createSharkTest() {
		Shark input = new Shark("Great White", "Shark Shark", "Ocean", "Extinct");
		Shark output = new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct");
	
		Mockito.when(this.repo.save(input)).thenReturn(output);
		
		assertEquals(output, this.service.create(input));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	@Test 
	public void readByIdTest() {
		Optional<Shark> optionalOuput = Optional.of(new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct"));
		Shark output = new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct");
		
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOuput);
		
		assertEquals(output, this.service.readById(Mockito.anyLong()));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	public void findSharkBySpeciesTest() {
		List<Shark> outputList = new ArrayList<>();
		Shark output = new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct");
		outputList.add(output);
		
		Mockito.when(this.repo.findSharkBySpecies("Great White")).thenReturn(outputList);
		
		assertEquals(outputList, this.service.findSharkBySpecies("Great White"));
		
		Mockito.verify(this.repo, Mockito.times(1)).findSharkBySpecies("Great White");
	}
	
	@Test
	public void findSharkByHabitatSQL() {
		List<Shark> outputList = new ArrayList<>();
		Shark output = new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct");
		outputList.add(output);
		
		Mockito.when(this.repo.findSharkByHabitatSQL("Ocean")).thenReturn(outputList);
		
		assertEquals(outputList, this.service.findSharkByHabitatSQL("Ocean"));
		
		Mockito.verify(this.repo, Mockito.times(1)).findSharkByHabitatSQL("Ocean");
	}
	
	@Test
	public void findSharkByConservationStatusJPQL() {
		List<Shark> outputList = new ArrayList<>();
		Shark output = new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct");
		outputList.add(output);
		
		Mockito.when(this.repo.findSharkByConservationStatusJPQL("Extinct")).thenReturn(outputList);
		
		assertEquals(outputList, this.service.findSharkByConservationStatusJPQL("Extinct"));
		
		Mockito.verify(this.repo, Mockito.times(1)).findSharkByConservationStatusJPQL("Extinct");
	}
	
	@Test
	public void readAllTest() {
		List<Shark> outputList = new ArrayList<>();
		Shark output = new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct");
		outputList.add(output);
		
		Mockito.when(this.repo.findAll()).thenReturn(outputList);
		
		assertEquals(outputList, this.service.readAll());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void updateTest() {
		Optional<Shark> optionalOuput = Optional.of(new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct"));
		Shark output = new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct");
		
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOuput);
		
		assertEquals(output, this.service.readById(Mockito.anyLong()));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
		
		Shark updatedOutput = new Shark(1L, "Great White", "Shark Shark", "Ocean", "Extinct");
		
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(updatedOutput);
		
		assertEquals(updatedOutput, this.service.update(1L, updatedOutput));
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(output);
	}
	
	@Test
	public void deleteTrueTest() {
		Mockito.when(this.repo.existsById(Mockito.anyLong())).thenReturn(false);
		
		assertTrue(this.service.delete(Mockito.anyLong()));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(Mockito.anyLong());
		Mockito.verify(this.repo, Mockito.times(1)).existsById(Mockito.anyLong());
	}
	
	@Test
	public void deleteFalseTest() {
		Mockito.when(this.repo.existsById(Mockito.anyLong())).thenReturn(true);
		
		assertFalse(this.service.delete(Mockito.anyLong()));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(Mockito.anyLong());
		Mockito.verify(this.repo, Mockito.times(1)).existsById(Mockito.anyLong());
	}

}
