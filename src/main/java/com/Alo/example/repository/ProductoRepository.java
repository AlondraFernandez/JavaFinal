package com.Alo.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Alo.example.entity.Product;

public interface ProductoRepository extends JpaRepository<Product,Long>{ }
