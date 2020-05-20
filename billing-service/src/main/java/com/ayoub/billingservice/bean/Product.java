package com.ayoub.billingservice.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {

    private Long id;
    private String name;
    private double price;
}
