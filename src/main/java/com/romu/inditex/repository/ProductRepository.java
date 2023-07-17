package com.romu.inditex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romu.inditex.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
