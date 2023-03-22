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

    public CustomerDto getCustomer(Long id) {
        return customerDtoConverter.convert(getCustomerById(id));
    }

    public CustomerDto getCustomer(String mail){
        return customerDtoConverter.convert(getCustomerByMail(mail));
    }

    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer(
                createCustomerRequest.getMail(),
                createCustomerRequest.getFirstName(),
                createCustomerRequest.getLastName(),
                createCustomerRequest.getAddress()
        );
        customerRepository.save(customer);
        return customerDtoConverter.convert(customer);
    }

    public CustomerDto updateCustomer(UpdateCustomerRequest updateCustomerRequest) {
        Customer updatedCustomer = new Customer(
                updateCustomerRequest.getId(),
                updateCustomerRequest.getMail(),
                updateCustomerRequest.getFirstName(),
                updateCustomerRequest.getLastName(),
                updateCustomerRequest.getAddress(),
                true);
        customerRepository.save(updatedCustomer);
        return customerDtoConverter.convert(updatedCustomer);
    }

    public void changeActivityCustomer(Long id){
        Customer customer = getCustomerById(id);
        if (customer.isActive()) {
            setActive(customer,false);
        } else {
            setActive(customer,true);
        }
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }
        else{throw new CustomerNotFoundException(id);}

    }

    protected Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );
    }

    protected Customer getCustomerByMail(String mail){
        return customerRepository.findByMail(mail).orElseThrow(
                () -> new CustomerNotFoundException("Customer could not be found by mail" + mail)
        );
    }

    protected Customer setActive(Customer customer, Boolean status){
        Customer updatedCustomer = new Customer(
                customer.getId(),
                customer.getMail(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                status
        );
        customerRepository.save(updatedCustomer);
        return updatedCustomer;
    }
}
