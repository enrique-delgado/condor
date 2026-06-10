package com.condor.customersmanager.service;

import com.condor.customersmanager.persistence.entity.CustomerEntity;
import com.condor.customersmanager.persistence.repository.CustomerRepository;
import com.condor.customersmanager.dto.service.Customer;
import com.condor.customersmanager.exception.CustomerNotFoundException;
import com.condor.customersmanager.mapper.CustomerServiceMapper;
import com.condor.customersmanager.util.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerServiceMapper mapper;

    /**
     * Create a new customer with encoded password.
     */
    public Customer create(Customer customer) {
        customer.setPassword(PasswordEncoderUtil.encode(customer.getPassword()));
        CustomerEntity entity = mapper.toEntity(customer);
        return mapper.toDomain(repository.save(entity));
    }

    /**
     * Find a customer by ID.
     */
    public Customer findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    /**
     * Find all customers.
     */
    public List<Customer> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    /**
     * Update an existing customer.
     */
    public Customer update(Long id, Customer customer) {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setAddress(customer.getAddress());
        entity.setPhone(customer.getPhone());
        entity.setActive(customer.getActive());

        if (customer.getPassword() != null && !customer.getPassword().isBlank()) {
            entity.setPassword(PasswordEncoderUtil.encode(customer.getPassword()));
        }

        return mapper.toDomain(repository.save(entity));
    }

    /**
     * Deactivate a customer by ID.
     */
    public void deactivate(Long id) {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        entity.setActive(false);
        repository.save(entity);
    }

    /**
     * Delete a customer by ID.
     */
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
