package com.ayoub.billingservice.restservice;

import com.ayoub.billingservice.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryService {

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable Long id);

    @GetMapping("/products")
    public PagedModel<Product> findAllProducts();
}
