package com.simplilearn.sportyshoe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.sportyshoe.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
