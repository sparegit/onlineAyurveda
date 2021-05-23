package com.cg.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.spring.model.Payment;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

}
