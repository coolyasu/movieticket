package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
//	@NotEmpty(message = "Name can't be empty")
	@Column(name="ITEM_NAME" )
	private String itemName;
	
	@Column(name="ITEM_CATEGORY")
//	@Size(min = 2,max = 50,message = "Item Description should be betwwen 2 to 50 chars")
	private String itemCategory;
	
	@Column(name="ITEM_QUANTITY")
//	@Positive(message = "Quantity can't be negative")
	private int itemQuantity;
	
	@Column(name="DEALER_NAME")
	private String dealerName;	
	
	@Column(name="DEALER_MOBNO")
	private String dealerMobNo;
	
	@Column(name="DEALER_EMAILID")
	private String dealerEmailId;
	
	@Column(name="DEALER_LOCATION")
	private String dealerLocation;
	
}
