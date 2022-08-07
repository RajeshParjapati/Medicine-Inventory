package com.inventory.controller;

import com.inventory.entity.Product;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all/{name}/{offset}/{pageSize}")
    public ResponseEntity<List<Product>> getAllProductIsInStock(@PathVariable String name,
                                                                @PathVariable int offset,
                                                                @PathVariable int pageSize){
        return ResponseEntity.ok(productService.findBySupplierContaining(name,offset,pageSize));
    }

    @GetMapping("/all/{name}/{productName}/{offset}/{pageSize}")
    public ResponseEntity<List<Product>> getAllProductIsInStockWithSerch(@PathVariable String name,
                                                                         @PathVariable String productName,
                                                                         @PathVariable int offset,
                                                                         @PathVariable int pageSize){
        return ResponseEntity.ok(productService.findAllByName(name,productName,offset,pageSize));
    }

    @GetMapping("/allactive/{offset}/{pageSize}")
    public ResponseEntity<List<Product>> getAllProductIsInStock(@PathVariable int offset,
                                                                @PathVariable int pageSize){
        return ResponseEntity.ok(productService.findAllActive(offset,pageSize));
    }

}
