package com.order.service.orderservice.service;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.service.orderservice.request.dtos.OrderItemRequestDto;


@FeignClient(name= "order-item",url="localhost:3001")
public interface RemoteOrderItemService {
	
	@PutMapping(value="/order/items/insertOrderItems",produces="application/json;charset=UTF-8")
	public ResponseEntity<String> insertOrderItemDetails(@Valid @RequestBody OrderItemRequestDto orderItemRequestDto);
	
	@GetMapping(value="/order/items/getOrderItems/{orderId}",produces="application/json;charset=UTF-8")
	public ResponseEntity<OrderItemRequestDto> getOrderItemDetails(@PathVariable ("orderId") int orderId);

}
