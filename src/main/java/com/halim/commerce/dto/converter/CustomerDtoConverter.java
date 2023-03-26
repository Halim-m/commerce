package com.halim.commerce.dto.converter;

import com.halim.commerce.dto.CustomerDto;
import com.halim.commerce.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
    public CustomerDto convert(Customer from) {
        return new CustomerDto(
                from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                from.getAddress()
        );
    }

    public List<CustomerDto> convert(List<Customer> list) {

        return list.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
