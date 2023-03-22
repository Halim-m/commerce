package com.halim.commerce.dto.converter;

import com.halim.commerce.dto.CustomerDto;
import com.halim.commerce.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public CustomerDto convert(Customer from){
        return new CustomerDto(
                from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                from.getAddress()
        );
    }
}
