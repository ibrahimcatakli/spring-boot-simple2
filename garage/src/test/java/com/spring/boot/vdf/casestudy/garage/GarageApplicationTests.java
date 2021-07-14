package com.spring.boot.vdf.casestudy.garage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spring.boot.vdf.casestudy.garage.controller.GarageController;
import com.spring.boot.vdf.casestudy.garage.domain.Jeep;
import com.spring.boot.vdf.casestudy.garage.domain.Vehicle;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

@SpringBootTest
@AutoConfigureMockMvc
class GarageApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	Vehicle vehicle = new Jeep("34-UZ-7658", "Blue");

	@Test
	void contextLoads() {
	}

	@MockBean
	private GarageController garageController;

	@Test
	public void parkingShouldReturnDefaultMessage() throws Exception {

		String bookInJson = "{ \"registrationNumber\": \"34-SO-1988\", \"vehicleType\": \"Car\",  \"color\": \"Black\" }";
		postRequestPerform(bookInJson);

		bookInJson = "{ \"registrationNumber\": \"34-BO-1987\", \"vehicleType\": \"Jeep\",  \"color\": \"Blue\" }";
		postRequestPerform(bookInJson);

		bookInJson = "{ \"registrationNumber\": \"34-HBO-2020\", \"vehicleType\": \"Truck\",  \"color\": \"Black\" }";
		postRequestPerform(bookInJson);

		int slot = 3;

		deleteRequestPerform(slot);

	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception {

		String bookInJson = "{ \"registrationNumber\": \"34-VO-2019\", \"vehicleType\": \"Truck\",  \"color\": \"Red\" }";
		System.err.println(bookInJson);
		postRequestPerform(bookInJson);
	}

	@Test
	public void statusShouldReturnDefaultMessage() throws Exception {
		getRequestPerform("status");
	}

	private void postRequestPerform(String bookInJson) throws Exception {
		this.mockMvc
				.perform(post("/api").accept(MediaType.APPLICATION_JSON).content(bookInJson)
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	private void deleteRequestPerform(int path) throws Exception {
	
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/leave/{parkingSlotNumber}" , path)				
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON));
	}

	private void getRequestPerform(String path) throws Exception {
		this.mockMvc.perform(get("/api/" + path).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
}
