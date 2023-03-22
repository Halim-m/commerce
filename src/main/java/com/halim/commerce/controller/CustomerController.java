package com.halim.commerce.controller;

import com.halim.commerce.dto.CustomerDto;
import com.halim.commerce.dto.request.CreateCustomerRequest;
import com.halim.commerce.dto.request.UpdateCustomerRequest;
import com.halim.commerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/customer", headers = "Accept=application/json")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomer(){
        List<CustomerDto> customers = customerService.getAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<CustomerDto> getCustomerById(@RequestParam("id") Long id){
        CustomerDto customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){
        CustomerDto createdCustomer = customerService.createCustomer(createCustomerRequest);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest){
        CustomerDto updatedCustomer = customerService.updateCustomer(updateCustomerRequest);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @PatchMapping("/activity/{id}")
    public ResponseEntity<CustomerDto> deactivateCustomer(@PathVariable("id") Long id){
        return ResponseEntity.ok(customerService.changeActivityCustomer(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.accepted().build();
    }
}
