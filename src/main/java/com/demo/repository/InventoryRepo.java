package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer>{

	public Inventory findByItemName(String itemName);
	
	public List<Inventory> findByItemNameAndDealerName(String itemName, String dealerName);

//	public Inventory findItemByName(String itemName);
	
}
