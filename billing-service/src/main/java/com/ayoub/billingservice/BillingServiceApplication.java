package com.ayoub.billingservice;

import com.ayoub.billingservice.bean.Bill;
import com.ayoub.billingservice.bean.Customer;
import com.ayoub.billingservice.bean.Product;
import com.ayoub.billingservice.bean.ProductItem;
import com.ayoub.billingservice.repository.BillRepository;
import com.ayoub.billingservice.repository.ProductItemRepository;
import com.ayoub.billingservice.restservice.CustomerService;
import com.ayoub.billingservice.restservice.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductItemRepository productItemRepository, BillRepository billRepository,
                            CustomerService customerService, InventoryService inventoryService){
        return args -> {
            Customer customer1 = customerService.findCustomerById(1L);
            System.out.println("********************************");
            System.out.println(customer1);
            System.out.println("********************************");
            Bill bill1 = billRepository.save(new Bill(1L, new Date(), customer1.getId(), null, null));

            PagedModel<Product> products = inventoryService.findAllProducts();
            products.getContent().forEach(product -> {
                productItemRepository.save(new ProductItem(null, product.getId(), null,  product.getPrice(), 5, bill1));
            });

        };
    }

}
