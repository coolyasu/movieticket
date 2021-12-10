package com.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.entity.Inventory;

import com.demo.repository.InventoryRepo;

@SpringBootTest
class InventoryServiceTest {

	@Autowired
	private InventoryService inventoryService;
	
	@MockBean
	private InventoryRepo inventoryRepo;
	
	@BeforeEach
	void setUp() {
		Inventory inventory = Inventory.builder().
				itemName("Watch").
				itemCategory("Fashion").
				itemQuantity(7).
				itemId(1).build();
		
		Mockito.when(inventoryRepo.findByItemName("Watch")).thenReturn(inventory);
		
		Optional<Inventory> inventory1 = Optional.ofNullable(Inventory.builder().
				itemName("Watch").
				itemCategory("Fashion").
				itemQuantity(7).
				itemId(1).build());
		Mockito.when(inventoryRepo.findById(1)).thenReturn(inventory1);
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Test
	@DisplayName("Get Item By itemName")
	public void whenValidItemName_ItemShouldfound() {
		String itemName = "Watch";
		Inventory found = inventoryService.showItemByName(itemName);
		assertEquals(itemName,found.getItemName());
		
	}
//	
//	@Test
//	public void whenValiditemId_ItemShouldFound() throws ItemNotFoundException {
//		int itemId=1;
//		Inventory found = inventoryService.showItemById(itemId);
//		assertEquals(itemId, found.getItemId());
//	}
}
