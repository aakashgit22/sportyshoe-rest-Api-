package com.simplilearn.sportyshoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.sportyshoe.model.Cart;
import com.simplilearn.sportyshoe.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
