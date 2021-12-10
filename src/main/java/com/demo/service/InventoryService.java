package com.demo.service;

import java.util.List;

import com.demo.entity.Inventory;


public interface InventoryService {

	public Inventory addItem(Inventory inventory);

	public List<Inventory> showItems();

	public Inventory showItemById(int itemId);

	public void deleteItemById(int itemId);

	public Inventory updateItem(int itemId, Inventory inventory);

	public Inventory showItemByName(String itemName);

	public Inventory updateItemUsingPatch(int itemId, Inventory inventory);

	public List<Inventory> showItemByItemNameAndDealerName(String itemName, String dealerName);



}
