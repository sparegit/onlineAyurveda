package com.cg.spring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.spring.model.Order;

public interface IOrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findAllOrderByOrderDate(LocalDate orderDate);

}