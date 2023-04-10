package com.andre.SpringRestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.SpringRestAPI.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
