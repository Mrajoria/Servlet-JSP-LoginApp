package com.model;

public class UserItem extends Item {
	
	String actioncode="pending";

	public UserItem() {
		// TODO Auto-generated constructor stub
	}

	public UserItem(String name, int quantity, String location, String status,String actioncode) {
		super(name, quantity, location, status);
		this.actioncode=actioncode;
	}

	public String getActioncode() {
		return actioncode;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}

}
