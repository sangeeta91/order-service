package com.order.service.orderservice.service;

import com.order.service.orderservice.request.dtos.OrderRequestDto;
import com.order.service.orderservice.response.dtos.OrderResponseDto;

public interface OrderService {
  
	public int insertOrderDetails(OrderRequestDto orderRequestDto);
	
	public OrderResponseDto getOrderItemDetails(int orderId);
}
