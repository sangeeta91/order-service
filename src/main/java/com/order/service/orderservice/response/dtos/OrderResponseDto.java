package com.order.service.orderservice.response.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.order.service.orderservice.request.dtos.ItemDto;

public class OrderResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private int orderId;

    private String customerName;
	
	private Date orderDate;
	
	private String shippingAddress;
	
	private List<ItemDto> orderItems;
	
	private int price;
	
	public OrderResponseDto() {}

	public OrderResponseDto(int orderId, String customerName, Date orderDate, String shippingAddress,
			List<ItemDto> orderItems, int price) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.orderItems = orderItems;
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
		return "OrderResponseDto [orderId=" + orderId + ", customerName=" + customerName + ", orderDate=" + orderDate
				+ ", shippingAddress=" + shippingAddress + ", orderItems=" + orderItems + ", price=" + price + "]";
	}
	
	
	
	
}
