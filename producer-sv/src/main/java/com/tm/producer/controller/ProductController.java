package com.tm.producer.controller;

import com.tm.commons.dto.ProductDTO;
import com.tm.producer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> produces(@RequestBody ProductDTO dto){

        productService.createProduct(dto);

        return ResponseEntity.status(CREATED).build();
    }


}
