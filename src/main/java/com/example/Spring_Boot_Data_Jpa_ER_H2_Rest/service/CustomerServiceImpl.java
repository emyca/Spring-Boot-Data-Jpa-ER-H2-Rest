package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Customer;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Location;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.CustomerMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.CustomerRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Customer create(CustomerDtoRequest request) {
        return customerRepository
                .saveAndFlush(customerMapper
                        .dtoCreateToEntity(request));
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Customer updateById(Long id, CustomerDtoRequest request) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Optional<Location> locationOptional = locationRepository.findById(id);
        return (customerOptional.isPresent() & locationOptional.isPresent()) ?
                customerRepository.saveAndFlush(
                        customerMapper.dtoUpdateByIdToEntity(
                                id, request,
                                customerOptional.get(),
                                locationOptional.get())) : null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
