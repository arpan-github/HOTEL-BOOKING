package com.book.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.hotel.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{

	public Payment findByOrderId(String orderId);
}
