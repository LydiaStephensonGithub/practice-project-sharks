package com.example.sharks.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class SharkTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Shark.class).usingGetClass().verify();
	}
	
	@Test
	public void getAndSetTest() {
		Shark shark = new Shark();
		
		shark.setId(1L);
		shark.setSpecies("Great White");
		shark.setLatinName("Sharkium Sharksus");
		shark.setHabitat("Ocean");
		shark.setConservationStatus("Extinct");
		
		assertNotNull(shark.getId());
		assertNotNull(shark.getSpecies());
		assertNotNull(shark.getLatinName());
		assertNotNull(shark.getHabitat());
		assertNotNull(shark.getConservationStatus());
		
		assertEquals(shark.getId(), 1L);
		assertEquals(shark.getSpecies(), "Great White");
		assertEquals(shark.getLatinName(), "Sharkium Sharksus");
		assertEquals(shark.getHabitat(), "Ocean");
		assertEquals(shark.getConservationStatus(), "Extinct");
	}
	
	@Test 
	public void allArgsConstructor() {
		Shark shark = new Shark(1L, "Great White", "Sharkium Sharksus", "Ocean", "Extinct");
		
		assertEquals(shark.getId(), 1L);
		assertEquals(shark.getSpecies(), "Great White");
		assertEquals(shark.getLatinName(), "Sharkium Sharksus");
		assertEquals(shark.getHabitat(), "Ocean");
		assertEquals(shark.getConservationStatus(), "Extinct");
	}
	
	@Test 
	public void noIdConstructor() {
		Shark shark = new Shark("Great White", "Sharkium Sharksus", "Ocean", "Extinct");
		
		assertEquals(shark.getSpecies(), "Great White");
		assertEquals(shark.getLatinName(), "Sharkium Sharksus");
		assertEquals(shark.getHabitat(), "Ocean");
		assertEquals(shark.getConservationStatus(), "Extinct");
	}
	
	@Test
	public void toStringTest() {
		Shark shark = new Shark(1, "Great White", "Sharkium Sharksus", "Ocean", "Extinct");
		
		assertEquals(shark.toString(), "1 Great WhiteSharkium SharksusOceanExtinct");
	}
	
}
