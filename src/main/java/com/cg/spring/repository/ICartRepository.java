package com.cg.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.spring.model.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {

}
