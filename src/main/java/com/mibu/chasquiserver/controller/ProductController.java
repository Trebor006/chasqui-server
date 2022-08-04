package com.mibu.chasquiserver.controller;

import com.mibu.chasquiserver.dto.ProductDto;
import com.mibu.chasquiserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductListFilteredByName(@RequestParam String productName) {
        List<ProductDto> result = productService.getProductFromElastic(productName);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDtoToSave) {
        ProductDto productDto = productService.saveNewProduct(productDtoToSave.getTitle(), productDtoToSave.getType());

        return ResponseEntity.ok().body(productDto);
    }
}
