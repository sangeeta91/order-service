package com.order.service.orderservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.order.service.orderservice.entity.OrderEntity;
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
	public int insertOrderDetails(OrderRequestDto orderRequestDto) throws Exception {
		int orderId;
		try {
			
			OrderEntity orderEntity=new OrderEntity(orderRequestDto.getCustomerName(),orderRequestDto.getShippingAddress(),orderRequestDto.getPrice());
			orderRepository.saveAndFlush(orderEntity);
			orderId=	orderEntity.getOrderId();
			OrderItemRequestDto orderItemRequestDto=new OrderItemRequestDto();
			orderItemRequestDto.setOrderId(orderId);
			orderItemRequestDto.setItems(orderRequestDto.getOrderItems());
			ResponseEntity<String> response=remoteOrderItemService.insertOrderItemDetails(orderItemRequestDto);
			if(!response.getStatusCode().equals(HttpStatus.CREATED)) {
				orderRepository.deleteById(orderId);
				throw new Exception();
			}
		}
		catch(Exception e) {
			
			throw e;
		}
		
		return orderId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public OrderResponseDto getOrderItemDetails(int orderId) throws Exception {
		Optional<OrderEntity> orderEntity =null;
		OrderResponseDto orderResponseDto= new OrderResponseDto();
		try {
			orderEntity=orderRepository.findById(orderId);
			if(orderEntity.isPresent()) {
			orderResponseDto.setCustomerName(orderEntity.get().getCustomerName());
			orderResponseDto.setOrderId(orderId);
			orderResponseDto.setShippingAddress(orderEntity.get().getShippingAddress());
			orderResponseDto.setPrice(orderEntity.get().getTotalPrice());
			ResponseEntity<OrderItemRequestDto> response=remoteOrderItemService.getOrderItemDetails(orderId);
			List<ItemDto> itemList=response.getBody().getItems();
			if(itemList.isEmpty()) {
				throw new Exception("Order Id not found");
 
			}
			}else {
				throw new Exception("Order Id not found");
			}
			
			
		}catch(Exception e) {
			throw e;
		}

		return orderResponseDto;
	}

}
