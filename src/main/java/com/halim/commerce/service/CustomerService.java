package com.halim.commerce.service;

import com.halim.commerce.dto.CustomerDto;
import com.halim.commerce.dto.converter.CustomerDtoConverter;
import com.halim.commerce.dto.request.CreateCustomerRequest;
import com.halim.commerce.dto.request.UpdateCustomerRequest;
import com.halim.commerce.exception.CustomerNotFoundException;
import com.halim.commerce.model.Customer;
import com.halim.commerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;


    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter){
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }


    public List<CustomerDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Long id) {
        return customerDtoConverter.convert(customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not be found by id " , id)
                )
        );
    }

    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer(
                createCustomerRequest.getMail(),
                createCustomerRequest.getFirstName(),
                createCustomerRequest.getLastName(),
                createCustomerRequest.getAddress()
        );
        customerRepository.save(customer);
        System.out.println(customer);//TODO
        return customerDtoConverter.convert(customer);
    }

    public CustomerDto updateCustomer(UpdateCustomerRequest updateCustomerRequest) {
        Long id = updateCustomerRequest.getId();
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer could not be found by id " , id)
        );
        customer.setMail(updateCustomerRequest.getMail());
        customer.setFirstName(updateCustomerRequest.getFirstName());
        customer.setLastName(updateCustomerRequest.getLastName());
        customer.setAddress(updateCustomerRequest.getAddress());
        customer.setActive(true);
        System.out.println(customer); //TODO
        return customerDtoConverter.convert(customer);
    }

    public CustomerDto changeActivityCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer could not be found by id ", id)
        );

        if (customer.isActive()) {
            System.out.println("Customer is deactivated");
            customer.setActive(false);
        } else {
            System.out.println("Customer is activated");
            customer.setActive(true);
        }
        return customerDtoConverter.convert(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(
                        ()-> new CustomerNotFoundException(id)
                );
        customerRepository.deleteById(id);
    }
}
