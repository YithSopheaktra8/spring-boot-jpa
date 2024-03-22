package org.example.itespringwebapi.service;

import org.example.itespringwebapi.dto.ProductCreateRequest;
import org.example.itespringwebapi.dto.ProductEditRequest;
import org.example.itespringwebapi.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findProducts();

    ProductResponse findProductById(Integer id);

    ProductResponse findProductByUuid(String uuid);

    ProductResponse editProductByUuid(String uuid, ProductEditRequest request);

    void deleteProductByUuid(String uuid);

    void createProduct(ProductCreateRequest request);

}
