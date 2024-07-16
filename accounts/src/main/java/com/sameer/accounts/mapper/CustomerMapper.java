package com.sameer.accounts.mapper;

import com.sameer.accounts.dto.CustomerDto;
import com.sameer.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto){
        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }

    public static  Customer mapTocustomer(CustomerDto customerDto, Customer customer){
        return Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber())
                .build();
    }
}
