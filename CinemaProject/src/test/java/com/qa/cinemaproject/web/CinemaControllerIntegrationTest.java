package com.qa.cinemaproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cinemaproject.domain.Cinema;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // loads context and boots up on different port
@AutoConfigureMockMvc // sets up the MockMVC objects
@Sql(scripts = { "classpath:cinema-schema.sql",
		"classpath:cinema-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class CinemaControllerIntegrationTest {
	
	@Autowired // pull the MockMvc object from the context (like with @Service or @Repository)
	private MockMvc mvc; // class that performs the request (it basically works as postman)

	@Autowired
	private ObjectMapper mapper; // java <-> JSON converter that spring uses

	@Test
	void testCreate() throws Exception {
		Cinema testCinema = new Cinema(null, "Odeon", "Batman", 10.99);
		String testCinemaAsJSON = this.mapper.writeValueAsString(testCinema);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testCinemaAsJSON);

		Cinema testCreatedCinema = new Cinema(5, "Odeon", "Batman", 10.99);
		String testCreatedCinemaAsJSON = this.mapper.writeValueAsString(testCreatedCinema);
		ResultMatcher checkStatus = status().isCreated(); // status 201 - created
		ResultMatcher checkBody = content().json(testCreatedCinemaAsJSON); // checks if the body matches my
																			// testCreatedPersonAsJson

		// sends the request and then checks the status and the body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}
	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		List<Cinema> testCinema = List.of(new Cinema(1, "Odeon","Batman",10.99), new Cinema(2, "Vue", "Batman", 5.99), new Cinema(3, "Cineworld", "Batman", 13.99), new Cinema(4, "Odeon", "Spiderman", 10.99));
        String json = this.mapper.writeValueAsString(testCinema);
        ResultMatcher checkStatus = status().isOk();
        ResultMatcher checkBody = content().json(json);
        
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void getTest() throws Exception {
		RequestBuilder req = get("/get/1");
		String cinemaAsJson = this.mapper.writeValueAsString(new Cinema(1, "Odeon", "Batman", 10.99));
		ResultMatcher checkStatus = status().isOk();
	    ResultMatcher checkBody = content().json(cinemaAsJson);
	    
	    this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	  	
	        
	}
	
	@Test
	void getByCinemaTest() throws Exception {
		RequestBuilder req = get("/getByCinema/Odeon");
		List<Cinema> testCinema = List.of(new Cinema(1, "Odeon", "Batman", 10.99), new Cinema(4, "Odeon", "Spiderman", 10.99));
		String json = this.mapper.writeValueAsString(testCinema);
		ResultMatcher checkStatus = status().isOk();
	    ResultMatcher checkBody = content().json(json);
	    
	    this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByFilmTest() throws Exception {
		RequestBuilder req = get("/getByFilm/Batman");
		List<Cinema> testCinema = List.of(new Cinema(1, "Odeon","Batman",10.99), new Cinema(2, "Vue", "Batman", 5.99), new Cinema(3, "Cineworld", "Batman", 13.99));
		String json = this.mapper.writeValueAsString(testCinema);
		ResultMatcher checkStatus = status().isOk();
	    ResultMatcher checkBody = content().json(json);
	    
	    this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByCostTest() throws Exception {
		RequestBuilder req = get("/getByCost/10.99");
		List<Cinema> testCinema = List.of(new Cinema(1, "Odeon","Batman",10.99), new Cinema(4, "Odeon", "Spiderman", 10.99));
		String json = this.mapper.writeValueAsString(testCinema);
		ResultMatcher checkStatus = status().isOk();
	    ResultMatcher checkBody = content().json(json);
	    
	    this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testReplace() throws Exception {
		Cinema testCinema = new Cinema(null, "Genesis","Toy Story", 6.99);
		String testCinemaAsJson = this.mapper.writeValueAsString(testCinema);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testCinemaAsJson);
		
		Cinema testCreatedCinema = new Cinema(1, "Genesis", "Toy Story", 6.99);
		String testCreatedCinemaAsJSON = this.mapper.writeValueAsString(testCreatedCinema);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedCinemaAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	@Test
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
	}


}
