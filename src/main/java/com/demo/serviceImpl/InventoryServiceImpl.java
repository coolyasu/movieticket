package com.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Inventory;
import com.demo.exception.RecordNotFoundException;
import com.demo.repository.InventoryRepo;
import com.demo.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepo inventoryRepo;

	@Override
	public Inventory addItem(Inventory inventory) {
		// TODO Auto-generated method stub
		return inventoryRepo.save(inventory);
	}

	@Override
	public List<Inventory> showItems() {
		// TODO Auto-generated method stub
		return inventoryRepo.findAll();
	}

	@Override
	public Inventory showItemById(int itemId) {
		// TODO Auto-generated method stub
		Optional<Inventory> inv = inventoryRepo.findById(itemId);
		if(inv.isPresent()) {
			return inventoryRepo.findById(itemId).get();
		}
		else {
			throw new RecordNotFoundException("Item is not available");
		}
	}

	@Override
	public void deleteItemById(int itemId) {
		// TODO Auto-generated method stub
		Optional<Inventory> inv = inventoryRepo.findById(itemId);
		if(inv.isPresent()) {
			inventoryRepo.deleteById(itemId);
		}
		else {
			throw new RecordNotFoundException("Item is not available");
		}
	}

	@Override
	public Inventory updateItem(int itemId, Inventory inventory) {
		// TODO Auto-generated method stub
		Optional<Inventory> inv = inventoryRepo.findById(itemId);
		
		if(inv.isPresent()) {
			Inventory updateInventory =inv.get();
			updateInventory.setItemName(inventory.getItemName());
			updateInventory.setItemCategory(inventory.getItemCategory());
			updateInventory.setItemQuantity(inventory.getItemQuantity());
			updateInventory.setDealerName(inventory.getDealerName());
			updateInventory.setDealerMobNo(inventory.getDealerMobNo());
			updateInventory.setDealerEmailId(inventory.getDealerEmailId());
			updateInventory.setDealerLocation(inventory.getDealerLocation());
			return inventoryRepo.save(updateInventory);
		}
		else {
			throw new RecordNotFoundException("Item is not available");
		}
		
	}


	@Override
	public Inventory showItemByName(String itemName) {
		// TODO Auto-generated method stub
		
		return inventoryRepo.findByItemName(itemName);
	}

	@Override
	public Inventory updateItemUsingPatch(int itemId, Inventory inventory) {
		// TODO Auto-generated method stub
	Optional<Inventory> inv = inventoryRepo.findById(itemId);
		
		if(inv.isPresent()) {
			Inventory updateInventory =inv.get();
			updateInventory.setItemName(inventory.getItemName());
			updateInventory.setItemCategory(inventory.getItemCategory());
			updateInventory.setItemQuantity(inventory.getItemQuantity());
			return inventoryRepo.save(updateInventory);
		}
		else {
			throw new RecordNotFoundException("Item is not available");
		}
	}

	@Override
	public List<Inventory> showItemByItemNameAndDealerName(String itemName, String dealerName) {
		// TODO Auto-generated method stub
		List<Inventory> lst = inventoryRepo.findByItemNameAndDealerName(itemName, dealerName);
//		if(lst.size()>0)
			return lst;
//		else
//			throw new BadRequestException() ;
	}

}
