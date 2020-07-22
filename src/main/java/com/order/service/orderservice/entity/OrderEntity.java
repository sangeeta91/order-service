package com.order.service.orderservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="order_tbl")
public class OrderEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	Integer orderId ;
	
	@Column(name="customer_name")
	String customerName ;
	
	@Column(name="shipping_address")
	String shippingAddress;
	
	@Column(name="total_price")
	int totalPrice ;
	
	@Column(name="order_date")
	@Temporal(TemporalType.TIMESTAMP)
	Date orderDate;
	
	protected OrderEntity() {}
	
	public OrderEntity(String customerName, String shippingAddress, int totalPrice) {
		this.customerName = customerName;
		this.shippingAddress = shippingAddress;
		this.totalPrice = totalPrice;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", customerName=" + customerName + ", shippingAddress="
				+ shippingAddress + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + "]";
	}
	
	
	

}
