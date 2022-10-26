package com.model;

public class Item {

	protected String name;
	protected String location;
	protected String status;
	protected int quantity;
	
	public Item() {
		
	}
	
	public Item(String name, int quantity, String location, String status) {
		super();
		this.name = name;
		this.location = location;
		this.status = status;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
