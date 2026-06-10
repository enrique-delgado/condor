package com.condor.customersmanager.service;

import com.condor.customersmanager.persistence.entity.CustomerEntity;
import com.condor.customersmanager.persistence.repository.CustomerRepository;
import com.condor.customersmanager.dto.service.Customer;
import com.condor.customersmanager.exception.CustomerNotFoundException;
import com.condor.customersmanager.mapper.CustomerServiceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerServiceMapper mapper;

    @InjectMocks
    private CustomerService service;

    private Customer domainCustomer;
    private CustomerEntity entity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        domainCustomer = new Customer();
        domainCustomer.setId(1L);
        domainCustomer.setFirstName("John");
        domainCustomer.setLastName("Doe");
        domainCustomer.setPassword("plainPass");

        entity = new CustomerEntity();
        entity.setId(1L);
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setPassword("encodedPass");
    }

    @Test
    void testCreateEncodesPassword() {
        when(mapper.toEntity(any(Customer.class))).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domainCustomer);

        Customer result = service.create(domainCustomer);

        assertEquals("John", result.getFirstName());
        verify(repository).save(entity);
    }

    @Test
    void testFindByIdSuccess() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domainCustomer);

        Customer result = service.findById(1L);

        assertEquals("John", result.getFirstName());
    }

    @Test
    void testFindByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domainCustomer);

        List<Customer> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void testUpdateSuccess() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domainCustomer);

        Customer updated = service.update(1L, domainCustomer);

        assertEquals("John", updated.getFirstName());
        verify(repository).save(entity);
    }

    @Test
    void testUpdateNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.update(1L, domainCustomer));
    }

    @Test
    void testDeactivate() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.deactivate(1L);

        assertFalse(entity.getActive());
        verify(repository).save(entity);
    }

    @Test
    void testDeleteByIdExists() {
        when(repository.existsById(1L)).thenReturn(true);

        boolean result = service.deleteById(1L);

        assertTrue(result);
        verify(repository).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotExists() {
        when(repository.existsById(1L)).thenReturn(false);

        boolean result = service.deleteById(1L);

        assertFalse(result);
        verify(repository, never()).deleteById(anyLong());
    }
}
