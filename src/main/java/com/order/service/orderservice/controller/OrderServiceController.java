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
		try {
			int OrderId=orderService.insertOrderDetails(orderRequestDto);
			return new ResponseEntity<>("order id: "+OrderId +" created successfully",HttpStatus.CREATED);

		}
		catch(Exception e) {
			return new ResponseEntity<>("insert_fail",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value="/getOrderDetails/{orderId}",produces="application/json;charset=UTF-8")
	public ResponseEntity<Object> getOrderDetails(@PathVariable ("orderId") int orderId){
		try {
			OrderResponseDto orderItemRequestDto=orderService.getOrderItemDetails(orderId);
			return new ResponseEntity<>(orderItemRequestDto,HttpStatus.OK);

		}
		catch(Exception e) {
			return new ResponseEntity<>("Error while retrieving data",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
