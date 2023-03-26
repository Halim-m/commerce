package com.halim.commerce;

import com.halim.commerce.dto.CustomerDto;
import com.halim.commerce.model.Customer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class TestUtil {
    static final Long staticId = 10L;

    public List<Customer> generateCustomerList() {
        return LongStream.range(0, 5).mapToObj(i ->
                new Customer(i,
                        i + "@test.com",
                        "firstName",
                        "lastName",
                        "address",
                        new Random(2).nextBoolean())
        ).collect(Collectors.toList());
    }

    public List<CustomerDto> generateCustomerDtoList(List<Customer> customerList) {
        return customerList.stream().map(
                from -> new CustomerDto(
                        from.getMail(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getAddress())
        ).collect(Collectors.toList());
    }

    public Customer generateCustomer(Long id) {
        return new Customer(
                id,
                id + "@gmail.com",
                "firstname" + id,
                "lastname" + id,
                "address" + id,
                true);
    }

    public Customer generateCustomer(String mail) {
        return new Customer(
                staticId,
                mail,
                "firstname",
                "lastname",
                "address",
                true);
    }

    public CustomerDto generateCustomerDto(Long id) {
        return new CustomerDto(
                id + "@gmail.com",
                "firstname" + id,
                "lastname" + id,
                "address" + id);
    }

    public CustomerDto generateCustomerDto(String mail) {
        return new CustomerDto(
                mail,
                "firstname",
                "lastname",
                "address");
    }
}
