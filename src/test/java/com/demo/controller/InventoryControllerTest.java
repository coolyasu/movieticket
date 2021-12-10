package com.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.entity.Inventory;
import com.demo.service.InventoryService;


@WebMvcTest(controllers = InventoryController.class)
class InventoryControllerTest {


	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InventoryService inventoryService;
	
	private Inventory inventory;
	
	@BeforeEach
	void setUp()  {
		
		inventory = Inventory.builder()
				.itemName("Watch")
				.itemCategory("Fashion")
				.itemQuantity(3)
				.itemId(1)
				.build();
	}

	@Test
	void addItem() throws Exception {
		Inventory inputInventory = Inventory.builder()
				.itemName("Watch")
				.itemCategory("Fashion")
				.itemQuantity(3)
				.build();
		
		Mockito.when(inventoryService.addItem(inputInventory)).thenReturn(inventory);
		
		mockMvc.perform(post("/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "	 \"itemName\": \"Watch\",\r\n"
						+ "	 \"itemCategory\":\"Fashion\",\r\n"
						+ "	 \"quantity\":3\r\n"
						+ "     \r\n"
						+ "}"))
		.andExpect(status().isOk());	 
	}

	@Test 
	void ShowItemById() throws Exception {
		Mockito.when(inventoryService.showItemById(1)).thenReturn(inventory);
		mockMvc.perform(get("/show/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.itemName").value(inventory.getItemName()));

	}

}
