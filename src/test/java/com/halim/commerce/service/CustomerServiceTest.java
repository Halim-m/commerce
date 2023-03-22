package com.halim.commerce.service;

import com.halim.commerce.dto.converter.CustomerDtoConverter;
import com.halim.commerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private CustomerDtoConverter converter;
    private CustomerRepository repository;
    private CustomerService service;

    @BeforeEach
    public void setUp(){
        converter = Mockito.mock(CustomerDtoConverter.class);
        repository = Mockito.mock(CustomerRepository.class);

        service = new CustomerService(repository,converter);
    }

    @Test
    void testGetAllCustomer_itShouldReturnCustomerDtoList() {
        //Mockito.when(repository.findAll()).thenReturn();
    }

    @Test
    void getCustomerById() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void changeActivityCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}
