package com.order.service.orderservice.request.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class OrderItemRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "OrderId is mandatory")
	private int OrderId;
	
	private List<ItemDto> items;

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + OrderId;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		OrderItemRequestDto other = (OrderItemRequestDto) obj;
		if (OrderId != other.OrderId)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderItemRequestDto [OrderId=" + OrderId + ", items=" + items + "]";
	}
	
	
     
}
