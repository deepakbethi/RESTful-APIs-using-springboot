package com.example.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.stereotype.Service;

import com.example.OrderService.entity.Order;
import com.example.OrderService.external.client.PaymentService;
import com.example.OrderService.external.client.ProductService;
import com.example.OrderService.external.request.PaymentRequest;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.repository.OrderRepository;
import com.netflix.discovery.converters.Auto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	

	@Override
	public long placeOrder(OrderRequest orderRequest) {
		
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		
		Order order=Order.builder()
				.amount(orderRequest.getTotalAmount())
				.orderStatus("CREATED")
				.productId(orderRequest.getProductId())
				.orderDate(Instant.now())
				.quantity(orderRequest.getQuantity())
				.build();
		
		order=orderRepository.save(order);
		log.info("calling payment service");
		
		PaymentRequest paymentRequest= PaymentRequest.builder()
				.orderId(order.getId())
				.paymentMode(orderRequest.getPaymentMode())
				.amount(orderRequest.getTotalAmount())
				.build();
		String orderStatus =null;
		
		try {
			paymentService.doPayment(paymentRequest);
			log.info("sucess");
			orderStatus = "placed";
		} catch (Exception e) {
			log.error("error in payment service");
			orderStatus="failed";
		}
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		
		log.info("order created");
		return order.getId();
		
	}



	@Override
	public OrderResponse getOrderDetails(long orderId) {
		// TODO Auto-generated method stub
		Order order=orderRepository.findById(orderId)
				.orElseThrow(()-> new RuntimeException("Order not found"));
		
		OrderResponse orderResponse=OrderResponse.builder()
				.orderId(order.getId())
				.amount(order.getAmount())
				.orderStatus(order.getOrderStatus())
				.orderDate(order.getOrderDate())
				.build();
		
		return orderResponse;
	}

}
