package com.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.constant.URIConst;
import com.demo.entity.Inventory;

import com.demo.service.InventoryService;
import com.demo.validator.JSONValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.FalseValidator;


@RestController

public class InventoryController {
	Logger logger = Logger.getLogger(Inventory.class);
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	JSONValidator jsonValidator = new JSONValidator();
	
	@Autowired
	private InventoryService inventoryService;
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(URIConst.ADD_ITEM)
	public ResponseEntity<Inventory> addItem(@Valid @RequestBody JsonNode inventory) throws JsonProcessingException, IOException {
		logger.error("Inside addItem method");
		jsonValidator.ValidateData(objectMapper.writeValueAsString(inventory));
		return new ResponseEntity<Inventory>(inventoryService.addItem(objectMapper.treeToValue(inventory, Inventory.class)),HttpStatus.CREATED);
	}

	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(URIConst.SHOW_ITEM)
	public List<Inventory> showItems() {
		logger.info("Inside showItem method");
		return inventoryService.showItems();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(URIConst.SHOW_ITEM_BY_ID)
	public Inventory showItemById(@PathVariable("id") int itemId) {
		logger.info("Inside showItemById method");
		return inventoryService.showItemById(itemId);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(URIConst.SHOW_ITEM_BY_NAME)
	public Inventory showItemByName(@PathVariable("name") String itemName) {
		logger.info("Inside showItemByName method");
		return inventoryService.showItemByName(itemName);
	}
	
//	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(URIConst.SHOW_ITEM_BY_ITEMNAME_BY_DEALERNAME)
	public List<Inventory> showItemByItemNameAndDealerName(@RequestParam(name= "itemName" , required = true) @Size(min=2,message = "Item Name should not be empty") String itemName ,
													 @RequestParam(name="dealerName",required = true ) @Size(min=2,message = "Dealer Name should not be empty") String dealerName) {
		return inventoryService.showItemByItemNameAndDealerName(itemName,dealerName);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(URIConst.DELETE_ITEM)
	public String deleteItemById(@RequestParam("id") int itemId) {
		logger.info("Inside deleteItemById method");
		inventoryService.deleteItemById(itemId);
		return "Deleted Successfully";
	}
	
	@PutMapping(URIConst.UPDATE_ITEM)
	public Inventory updateItem(@PathVariable("id") int itemId, @RequestBody Inventory inventory) {
		logger.info("Inside updateItem method");
		return inventoryService.updateItem(itemId, inventory);
	}
	
	@PatchMapping(URIConst.UPDATE_ITEM_USING_PATCH)
	public Inventory updateItemUsingPatch(@PathVariable("id") int itemId, @RequestBody Inventory inventory) {
		logger.info("Inside updateItem method");
		return inventoryService.updateItemUsingPatch(itemId, inventory);
	}
	
}
