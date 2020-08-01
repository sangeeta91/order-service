package com.order.service.orderservice.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.order.service.orderservice.entity.OrderEntity;
import com.order.service.orderservice.exceptions.OrderNotFoundException;
import com.order.service.orderservice.repository.OrderRepository;
import com.order.service.orderservice.request.dtos.ItemDto;
import com.order.service.orderservice.request.dtos.OrderItemRequestDto;
import com.order.service.orderservice.request.dtos.OrderRequestDto;
import com.order.service.orderservice.response.dtos.OrderResponseDto;
import com.order.service.orderservice.service.OrderService;
import com.order.service.orderservice.service.RemoteOrderItemService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	RemoteOrderItemService remoteOrderItemService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insertOrderDetails(OrderRequestDto orderRequestDto){
		int orderId;

		OrderEntity orderEntity=new OrderEntity(orderRequestDto.getCustomerName(),orderRequestDto.getShippingAddress(),orderRequestDto.getPrice(),new Date());
		orderRepository.saveAndFlush(orderEntity);
		orderId=	orderEntity.getOrderId();
		OrderItemRequestDto orderItemRequestDto=new OrderItemRequestDto();
		orderItemRequestDto.setOrderId(orderId);
		orderItemRequestDto.setItems(orderRequestDto.getOrderItems());
		ResponseEntity<String> response=remoteOrderItemService.insertOrderItemDetails(orderItemRequestDto);
		if(!response.getStatusCode().equals(HttpStatus.CREATED)) {
			orderRepository.deleteById(orderId);
			orderId=0;
		}
		return orderId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public OrderResponseDto getOrderItemDetails(int orderId){
		Optional<OrderEntity> orderEntity =null;
		OrderResponseDto orderResponseDto= null;
		orderEntity=orderRepository.findById(orderId);
		if(orderEntity.isPresent()) {
			orderResponseDto= new OrderResponseDto();
			orderResponseDto.setCustomerName(orderEntity.get().getCustomerName());
			orderResponseDto.setOrderId(orderId);
			orderResponseDto.setShippingAddress(orderEntity.get().getShippingAddress());
			orderResponseDto.setPrice(orderEntity.get().getTotalPrice());
			orderResponseDto.setOrderDate(orderEntity.get().getOrderDate());
			ResponseEntity<OrderItemRequestDto> response=remoteOrderItemService.getOrderItemDetails(orderId);
			List<ItemDto> itemList=response.getBody().getItems();
			if(itemList.isEmpty()) {
				orderResponseDto=null; 
			}else {
				orderResponseDto.setOrderItems(itemList);
			}
		}
		return orderResponseDto;
	}

}
