package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(CustomerDtoRequest request);
    List<Customer> getAll();
    Customer getById(Long id);
    Customer updateById(Long id, CustomerDtoRequest request);
    boolean deleteById(Long id);
}
