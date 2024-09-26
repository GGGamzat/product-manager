package com.example.product_manager.controller;

import com.example.product_manager.entity.Product;
import com.example.product_manager.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public  class  ProductController {

    private  final ProductService productService;

    public  ProductController (ProductService productService) {
        this .productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct (@RequestBody Product product) {
        Product  savedProduct  = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts () {
        List<Product> products = productService.fetchAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable Long id) {
        Optional<Product> productOptional = productService.fetchProductById(id);
        return productOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/product/{productId}")
    public ResponseEntity<Product> updateProduct ( @PathVariable Long id, @RequestBody Product product) {
        Optional<Product> updatedProductOptional = productService.updateProduct(id, product);
        return updatedProductOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity<String> deleteProduct ( @PathVariable Long id) {
        boolean  deletionStatus  = productService.deleteProduct(id);
        if (deletionStatus) {
            return ResponseEntity.ok( "Продукт с идентификатором " + id + " успешно удален" );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Не удалось удалить продукт с идентификатором " + id);
        }
    }
}