package org.example.itespringwebapi.repository;

import org.example.itespringwebapi.dto.ProductResponse;
import org.example.itespringwebapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByUuid(String uuid);

    Product findProductByUuid(String uuid);

    void deleteByUuid(String uuid);

}
