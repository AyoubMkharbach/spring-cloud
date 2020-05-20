package com.ayoub.customerservice.repository;

import com.ayoub.customerservice.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Ayoub MKHARBACH
 * @since 14/05/2020
 */
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
