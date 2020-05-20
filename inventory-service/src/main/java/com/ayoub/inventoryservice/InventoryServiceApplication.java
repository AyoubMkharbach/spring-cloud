package com.ayoub.inventoryservice;

import com.ayoub.inventoryservice.bean.Product;
import com.ayoub.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

/**
 * @author Ayoub MKHARBACH
 * Access to H2 console : http://localhost:8082/h2-console (with jdbc url : jdbc:h2:mem:testdb)
 */
@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(null, "Lenovo T450", 400));
            productRepository.save(new Product(null, "Xiaomi Note 10", 379));
            productRepository.save(new Product(null, "Or bartista", 99));
            productRepository.findAll().forEach(System.out::println);
        };
    }

}
