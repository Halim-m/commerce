package com.halim.commerce.service;
/*


import com.halim.commerce.TestUtil;
import com.halim.commerce.dto.CustomerDto;
import com.halim.commerce.dto.converter.CustomerDtoConverter;
import com.halim.commerce.dto.request.CreateCustomerRequest;
import com.halim.commerce.dto.request.UpdateCustomerRequest;
import com.halim.commerce.exception.CustomerNotFoundException;
import com.halim.commerce.model.Customer;
import com.halim.commerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest extends TestUtil {
    private CustomerDtoConverter converter;
    private CustomerRepository repository;
    private CustomerService service;

    @BeforeEach
    public void setUp() {
        converter = mock(CustomerDtoConverter.class);
        repository = mock(CustomerRepository.class);

        service = new CustomerService(repository, converter);
    }

    @Test
    void testGetAllCustomer_itShouldReturnCustomerDtoList() {
        List<Customer> customerList = generateCustomerList();
        List<CustomerDto> customerDtoList = generateCustomerDtoList(customerList);

        when(repository.findAll()).thenReturn(customerList);
        when(converter.convert(customerList)).thenReturn(customerDtoList);

        List<CustomerDto> result = service.getAllCustomer();

        assertEquals(customerDtoList, result);
        verify(repository).findAll();
        verify(converter).convert(customerList);
    }

    @Test
    void testGetCustomerById_whenCustomerIdExist_itShouldReturnCustomerDto() {
        Long id = 1L;
        Customer customer = generateCustomer(id);
        CustomerDto customerDto = generateCustomerDto(id);

        when(repository.findById(id)).thenReturn(Optional.of(customer));
        when(converter.convert(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomer(id);

        assertEquals(customerDto, result);
        verify(repository).findById(id);
        verify(converter).convert(customer);
    }

    @Test
    void testGetCustomerById_whenCustomerIdDoesNotExist_itShouldThrowUserNotFoundException() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () ->
                service.getCustomer(id)
        );

        verify(repository).findById(id);
        verifyNoInteractions(converter);
    }

    @Test
    void testGetCustomerByMail_whenCustomerMailExist_itShouldReturnCustomerDto() {
        String mail = "test@example.com";
        Customer customer = generateCustomer(mail);
        CustomerDto customerDto = generateCustomerDto(mail);

        when(repository.findByMail(mail)).thenReturn(Optional.of(customer));
        when(converter.convert(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomer(mail);

        assertEquals(customerDto, result);
        verify(repository).findByMail(mail);
        verify(converter).convert(customer);
    }

    @Test
    void testGetCustomerByMail_whenCustomerMailDoesNotExist_itShouldThrowUserNotFoundException() {
        String mail = "test@example.com";

        when(repository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () ->
                service.getCustomer(mail)
        );

        verify(repository).findByMail(mail);
        verifyNoInteractions(converter);
    }

    @Test
    void testCreateCustomer_itShouldReturnCreatedUserDto() {
        CreateCustomerRequest request = new CreateCustomerRequest(
                "mail@gmail.com",
                "firstName",
                "lastName",
                "address");
        Customer customer = new Customer(
                "mail@gmail.com",
                "firstName",
                "lastName",
                "address");
        Customer savedCustomer = new Customer(
                "mail@gmail.com",
                "firstName",
                "lastName",
                "address");
        CustomerDto customerDto = new CustomerDto(
                "mail@gmail.com",
                "firstName",
                "lastName",
                "address");

        when(repository.save(customer)).thenReturn(savedCustomer);
        when(converter.convert(savedCustomer)).thenReturn(customerDto);

        CustomerDto result = service.createCustomer(request);

        assertEquals(result, customerDto);

        verify(repository).save(customer);
        verify(converter).convert(customer);
    }

    @Test
    void testUpdateCustomer_whenCustomerIdExist_itShouldReturnUpdatedCustomerDto() {
        Long id = 1L;
        UpdateCustomerRequest request = new UpdateCustomerRequest(
                id,
                "updated@gmail.com",
                "updatedName",
                "updatedLastName",
                "updatedAddress");

        Customer updatedCustomer = new Customer(
                id,
                "updated@gmail.com",
                "updatedName",
                "updatedLastName",
                "updatedAddress",
                true);

        CustomerDto customerDto = new CustomerDto(
                "updated@gmail.com",
                "updatedName",
                "updatedLastName",
                "updatedAddress");

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(any(Customer.class))).thenReturn(updatedCustomer);
        when(converter.convert(updatedCustomer)).thenReturn(customerDto);

        CustomerDto result = service.updateCustomer(request);

        assertEquals(result, customerDto);

        verify(repository).save(any(Customer.class));
        verify(converter).convert(updatedCustomer);
    }

    @Test
    void testUpdateCustomer_whenCustomerIdNotExist_itShouldThrowCustomerNotFoundException() {
        Long id = 1L;
        UpdateCustomerRequest request = new UpdateCustomerRequest(
                id,
                "updated@gmail.com",
                "updatedName",
                "updatedLastName",
                "updatedAddress");

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> service.updateCustomer(request));

        verify(repository).existsById(id);
        verifyNoInteractions(converter);
    }

    @Test
    void testChangeActivityCustomer_whenCustomerIdExist() {
        Long id = 1L;
        Customer customer = new Customer(
                id,
                "test@example.com",
                "test",
                "test",
                "test",
                false);

        when(repository.findById(id)).thenReturn(Optional.of(customer));

        service.changeActivityCustomer(id);

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(customerCaptor.capture());
        Customer savedCustomer = customerCaptor.getValue();
        assertTrue(savedCustomer.isActive());
    }

    @Test
    void testChangeActivityCustomer_whenCustomerIdDoesNotExist() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> service.changeActivityCustomer(id));
    }

    @Test
    void testDeleteCustomer_whenCustomerIdExist() {
        Long id = 1L;

        when(repository.existsById(id)).thenReturn(true);

        service.deleteCustomer(id);

        verify(repository).deleteById(id);
    }

    @Test
    void testDeleteCustomer_whenCustomerIdDoesNotExist() {
        Long id = 1L;

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> service.deleteCustomer(id));
    }

    @Test
    void testGetCustomerById_whenCustomerIdExist() {
        Long id = 1L;
        Customer customer = new Customer(
                id,
                "test@example.com",
                "test",
                "test",
                "test",
                false);

        when(repository.findById(id)).thenReturn(Optional.of(customer));

        Customer result = service.getCustomerById(id);

        assertEquals(result, customer);
    }

    @Test
    void testGetCustomerById_whenCustomerIdDoesNotExist() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> service.getCustomerById(id));
    }

    @Test
    void testGetCustomerByMail_whenMailExist() {
        String mail = "test@example.com";
        Customer customer = new Customer(
                1L,
                mail,
                "test",
                "test",
                "test",
                false);

        when(repository.findByMail(mail)).thenReturn(Optional.of(customer));

        Customer result = service.getCustomerByMail(mail);

        assertEquals(result, customer);
    }

    @Test
    void testGetCustomerByMail_whenMailDoesNotExist() {
        String mail = "test@example.com";

        when(repository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> service.getCustomerByMail(mail));
    }

    @Test
    void testSetActive() {
        Long id = 1L;
        Customer customer = new Customer(
                id,
                "test@example.com",
                "test",
                "test",
                "test",
                false);

        service.setActive(customer, true);

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(customerCaptor.capture());
        Customer savedCustomer = customerCaptor.getValue();
        assertTrue(savedCustomer.isActive());
    }
}
*/
