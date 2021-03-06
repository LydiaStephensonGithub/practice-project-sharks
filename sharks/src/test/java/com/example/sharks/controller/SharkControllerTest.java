package com.example.sharks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.sharks.entity.Shark;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:shark-schema.sql", "classpath:shark-data.sql"})
public class SharkControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired ObjectMapper mapper;
	
	@Test
	public void testCreate() throws Exception {
		Shark testShark = new Shark("Shark", "Sharkium Sharksus", "New England", "Extinct");
		String testSharkAsJSON = this.mapper.writeValueAsString(testShark);
		RequestBuilder req = post("/shark/create").content(testSharkAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		Shark testSavedShark = new Shark(2, "Shark", "Sharkium Sharksus", "New England", "Extinct");
		String testSavedSharkAsJSON = this.mapper.writeValueAsString(testSavedShark);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testSavedSharkAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Shark testShark = new Shark(1, "Jaws", "Great White", "Ocean", "Vulnerable");
		String testSharkAsJSON = this.mapper.writeValueAsString(testShark);
		RequestBuilder req = put("/shark/update/1").content(testSharkAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testSharkAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testDelete() throws Exception {
		RequestBuilder req = delete("/shark/delete/1");
		
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(jsonPath("$").doesNotExist());
		
	}
	
	@Test
	public void testReadById() throws Exception {
		RequestBuilder req = get("/shark/readById/1");
		
		ResultMatcher checkStatus = status().isOk();
		
		Shark savedShark = new Shark(1, "Jaws", "Great White", "Ocean", "Extinct");
		String savedSharkAsJSON = this.mapper.writeValueAsString(savedShark);
		
		ResultMatcher checkBody = content().json(savedSharkAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReadAll() throws Exception {
		RequestBuilder req = get("/shark/readAll");
		
		ResultMatcher checkStatus = status().isOk();
		
		Shark savedShark = new Shark(1, "Jaws", "Great White", "Ocean", "Extinct");
		List<Shark> sharks = new ArrayList<Shark>();
		sharks.add(savedShark);
		String sharksAsJSON = this.mapper.writeValueAsString(sharks);		
		
		ResultMatcher checkBody = content().json(sharksAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testfindSharkBySpecies() throws Exception {
		RequestBuilder req = get("/shark/findSharkBySpecies/Jaws");
		
		ResultMatcher checkStatus = status().isOk();
		
		Shark savedShark = new Shark(1, "Jaws", "Great White", "Ocean", "Extinct");
		List<Shark> sharks = new ArrayList<Shark>();
		sharks.add(savedShark);
		String sharksAsJSON = this.mapper.writeValueAsString(sharks);
		
		ResultMatcher checkBody = content().json(sharksAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testfindSharkByHabitat() throws Exception {
		RequestBuilder req = get("/shark/findSharkByHabitatSQL/Ocean");
		
		ResultMatcher checkStatus = status().isOk();
		
		Shark savedShark = new Shark(1, "Jaws", "Great White", "Ocean", "Extinct");
		List<Shark> sharks = new ArrayList<Shark>();
		sharks.add(savedShark);
		String sharksAsJSON = this.mapper.writeValueAsString(sharks);
		
		ResultMatcher checkBody = content().json(sharksAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void findSharkByConservationStatus() throws Exception {
		RequestBuilder req = get("/shark/findSharkByConservationStatusJPQL/Extinct");
		
		ResultMatcher checkStatus = status().isOk();
		
		Shark savedShark = new Shark(1, "Jaws", "Great White", "Ocean", "Extinct");
		List<Shark> sharks = new ArrayList<Shark>();
		sharks.add(savedShark);
		String sharksAsJSON = this.mapper.writeValueAsString(sharks);
		
		ResultMatcher checkBody = content().json(sharksAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
}
