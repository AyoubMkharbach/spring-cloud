package com.ayoub.billingservice;

import com.ayoub.billingservice.bean.Bill;
import com.ayoub.billingservice.repository.BillRepository;
import com.ayoub.billingservice.repository.ProductItemRepository;
import com.ayoub.billingservice.restservice.CustomerService;
import com.ayoub.billingservice.restservice.InventoryService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/fullBill/{id}")
    @JsonSerialize
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.getOne(id);
        bill.setCustomer(customerService.findCustomerById(bill.getCustomerID()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryService.findProductById(productItem.getProductID()));
        });
        return bill;
    }
}
