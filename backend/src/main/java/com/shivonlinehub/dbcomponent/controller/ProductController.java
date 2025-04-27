package com.shivonlinehub.dbcomponent.controller;

import com.shivonlinehub.dbcomponent.model.Product;
import com.shivonlinehub.dbcomponent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
