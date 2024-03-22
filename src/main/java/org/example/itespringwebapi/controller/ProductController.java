package org.example.itespringwebapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.itespringwebapi.dto.ProductCreateRequest;
import org.example.itespringwebapi.dto.ProductEditRequest;
import org.example.itespringwebapi.dto.ProductResponse;
import org.example.itespringwebapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> findProducts(){
        return productService.findProducts();
    }

    @GetMapping("/{id}")
    ProductResponse findProductById(@PathVariable Integer id){
        return productService.findProductById(id);
    }

    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){
        productService.createProduct(request);
    }

    @PutMapping("/{uuid}")
    ProductResponse editProductByUuid(@Valid @RequestBody ProductEditRequest request, @PathVariable String uuid){
        return productService.editProductByUuid(uuid,request);
    }

    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteByUuid(uuid);
    }
}
