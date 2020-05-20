package com.ayoub.customerservice;

import com.ayoub.customerservice.bean.Customer;
import com.ayoub.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Customer.class);
            customerRepository.save(new Customer(null, "Atos", "contact@atos.net"));
            customerRepository.save(new Customer(null, "Datsup", "contact@datsup.fr"));
            customerRepository.save(new Customer(null, "UPSYS", "contact@upsys.ma"));
            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
