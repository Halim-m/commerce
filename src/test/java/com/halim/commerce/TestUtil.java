package com.halim.commerce;

import com.halim.commerce.dto.CustomerDto;
import com.halim.commerce.model.Customer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class TestUtil {
    public static List<Customer> generateCustomerList(){
        return LongStream.range(0,5).mapToObj(i->
            new Customer(i,
                    i+"@test.com" ,
                    "firstName",
                    "lastName",
                    "address",
                    new Random(2).nextBoolean())
        ).collect(Collectors.toList());
    }

    public static List<CustomerDto> generateCustomerDtoList(List<Customer> customerList){
        return customerList.stream().map(
                from -> new CustomerDto(
                        from.getMail(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getAddress())
                ).collect(Collectors.toList());}

}
