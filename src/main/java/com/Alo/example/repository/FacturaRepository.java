package com.Alo.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Alo.example.entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
