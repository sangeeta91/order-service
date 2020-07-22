package com.order.service.orderservice.request.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderRequestDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String customerName;
	
	private String shippingAddress;
	
	private List<ItemDto> orderItems;
	
	private int price;
	
	public OrderRequestDto() {}

	public OrderRequestDto(String customerName,String shippingAddress, List<ItemDto> orderItems,
			int price) {
		super();
		this.customerName = customerName;
		this.shippingAddress = shippingAddress;
		this.orderItems = orderItems;
		this.price = price;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<ItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<ItemDto> orderItems) {
		this.orderItems = orderItems;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderRequestDto [customerName=" + customerName + ", shippingAddress="
				+ shippingAddress + ", orderItems=" + orderItems + ", price=" + price + "]";
	}
	
	

}
