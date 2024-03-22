package org.example.itespringwebapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.itespringwebapi.dto.ProductCreateRequest;
import org.example.itespringwebapi.dto.ProductEditRequest;
import org.example.itespringwebapi.dto.ProductResponse;
import org.example.itespringwebapi.model.Product;
import org.example.itespringwebapi.repository.ProductRepository;
import org.example.itespringwebapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> findProducts() {
        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
         Product product =  productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product has not been found!"
        ));

         return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(), product.getQty());

    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        if(!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found!"
            );
        }
        Product product = productRepository.findProductByUuid(uuid);
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(), product.getQty());

    }

    @Override
    public ProductResponse editProductByUuid(String uuid, ProductEditRequest request) {
        if(!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found!"
            );
        }
        Product product = productRepository.findProductByUuid(uuid);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        productRepository.save(product);
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(), product.getQty());
    }

    @Override
    public void deleteProductByUuid(String uuid) {
        if(!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found!"
            );
        }
        productRepository.deleteByUuid(uuid);
    }

    @Override
    public void createProduct(ProductCreateRequest request) {
        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setImportedDate(LocalDateTime.now());
        product.setStatus(true);
        productRepository.save(product);
    }
}
