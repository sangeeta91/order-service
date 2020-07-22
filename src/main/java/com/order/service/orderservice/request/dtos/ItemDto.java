package com.order.service.orderservice.request.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull(message = "productCode is mandatory")
	private int productCode;
	@NotBlank(message = "productName cannot be empty or null")
	@Size(min=1)
	private String productName;
	@NotNull(message = "Quantity cannot be null")
	private int quantity;
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productCode;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + quantity;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDto other = (ItemDto) obj;
		if (productCode != other.productCode)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ItemDto [productCode=" + productCode + ", productName=" + productName + ", quantity=" + quantity + "]";
	}


}
