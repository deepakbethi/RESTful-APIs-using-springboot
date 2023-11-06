package com.example.PaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaymentService.entity.TransactionDetails;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
	TransactionDetails findByOrderId(long orderId);

}
