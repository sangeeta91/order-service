package com.order.service.orderservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.orderservice.exceptions.OrderNotFoundException;
import com.order.service.orderservice.request.dtos.OrderRequestDto;
import com.order.service.orderservice.response.dtos.OrderResponseDto;
import com.order.service.orderservice.service.OrderService;

@RequestMapping("/order")
@RestController
public class OrderServiceController {

	@Autowired
	OrderService orderService;
	@PutMapping(value="/insertOrderDetails",produces="application/json;charset=UTF-8")
	public ResponseEntity<String> insertOrderDetails(@Valid @RequestBody OrderRequestDto orderRequestDto){

		int OrderId=orderService.insertOrderDetails(orderRequestDto);
		if(OrderId!=0) {
			return new ResponseEntity<>("order id: "+OrderId +" created successfully",HttpStatus.CREATED);

		}		
		return new ResponseEntity<>("insert_fail",HttpStatus.INTERNAL_SERVER_ERROR);


	}

	@GetMapping(value="/getOrderDetails/{orderId}",produces="application/json;charset=UTF-8")
	public ResponseEntity<Object> getOrderDetails(@PathVariable ("orderId") int orderId){

		OrderResponseDto orderItemRequestDto=orderService.getOrderItemDetails(orderId);
		if(orderItemRequestDto==null) {
			throw new OrderNotFoundException("Order id:"+orderId+" not found in DB");
		}
		return new ResponseEntity<>(orderItemRequestDto,HttpStatus.OK);

	}

}
