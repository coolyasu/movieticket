package com.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.demo.entity.Inventory;

@DataJpaTest
class InventoryRepoTest {
	
	@Autowired
	private InventoryRepo inventoryRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setUp(){
		Inventory inventory = Inventory.builder()
				.itemName("Watch")
				.itemCategory("Fashion")
				.itemQuantity(3)
				.build();
		entityManager.persist(inventory);
	}
	
	@Test
	public void whenFindByitemId_thenReturnInventory() {
		Inventory inventory = inventoryRepo.findById(1).get();
		assertEquals(inventory.getItemName(), "Watch");
	}

	
}
