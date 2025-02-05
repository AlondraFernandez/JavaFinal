package com.Alo.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Alo.example.exception.InsufficientStockException;
import com.Alo.example.entity.Factura;
import com.Alo.example.entity.Product;
import com.Alo.example.model.FacturaRequest;
import com.Alo.example.Service.FacturaService;
import com.Alo.example.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productoService;

    @Autowired
    private FacturaService facturaService;

    @PostMapping ("/facturas")
    public ResponseEntity<Factura> crearFactura (@RequestBody FacturaRequest facturaRequest) {
        Factura factura = new Factura();
        factura = facturaService.crearNuevaFactura(factura, facturaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(factura);
    }

    // Obtener todos los productos
    @GetMapping("/productos")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productoService.findAll());
    }

    // Obtener un producto por ID
    @GetMapping("/productos/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> handleInsufficientStockException(InsufficientStockException
                                                                           ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }
}
