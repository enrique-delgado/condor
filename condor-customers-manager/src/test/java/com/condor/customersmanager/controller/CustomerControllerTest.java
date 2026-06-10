package com.condor.customersmanager.controller;

import com.condor.commons.exception.GlobalExceptionHandler;
import com.condor.customersmanager.persistence.repository.CustomerRepository;
import com.condor.customersmanager.dto.rest.CustomerRequest;
import com.condor.customersmanager.dto.rest.CustomerResponse;
import com.condor.customersmanager.dto.service.Customer;
import com.condor.customersmanager.exception.CustomerNotFoundException;
import com.condor.customersmanager.mapper.CustomerRestMapper;
import com.condor.customersmanager.service.CustomerService;
import com.condor.customersmanager.util.RandomFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@Import(GlobalExceptionHandler.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerRestMapper customerRestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private final EasyRandom easyRandom = RandomFactory.getEasyRandom();

    @Test
    void testGetAllCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");

        CustomerResponse response = new CustomerResponse();
        response.setId(1L);
        response.setFirstName("John");

        Mockito.when(customerService.findAll()).thenReturn(List.of(customer));
        Mockito.when(customerRestMapper.toResponse(customer)).thenReturn(response);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testGetCustomerByIdFound() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");

        CustomerResponse response = new CustomerResponse();
        response.setId(1L);
        response.setFirstName("John");

        Mockito.when(customerService.findById(1L)).thenReturn(customer);
        Mockito.when(customerRestMapper.toResponse(customer)).thenReturn(response);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testGetCustomerByIdNotFound() throws Exception {
        Mockito.when(customerService.findById(99L)).thenThrow(new CustomerNotFoundException(99L));

        mockMvc.perform(get("/customers/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Customer with id 99 was not found"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setFirstName("Jane");

        Customer customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Jane");

        CustomerResponse response = new CustomerResponse();
        response.setId(2L);
        response.setFirstName("Jane");

        Mockito.when(customerRestMapper.toDomain(any(CustomerRequest.class))).thenReturn(customer);
        Mockito.when(customerService.create(any(Customer.class))).thenReturn(customer);
        Mockito.when(customerRestMapper.toResponse(customer)).thenReturn(response);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCustomerRequest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.firstName").value("Jane"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");

        CustomerResponse response = new CustomerResponse();
        response.setId(1L);
        response.setFirstName("Johnny");

        Mockito.when(customerService.findById(1L)).thenReturn(customer);
        Mockito.when(customerService.update(eq(1L), any(Customer.class))).thenReturn(customer);
        Mockito.when(customerRestMapper.toResponse(customer)).thenReturn(response);

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCustomerRequest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Johnny"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        Mockito.when(customerService.deleteById(1L)).thenReturn(true);

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCustomerNotFound() throws Exception {
        Mockito.when(customerService.deleteById(99L)).thenReturn(false);

        mockMvc.perform(delete("/customers/99"))
                .andExpect(status().isNotFound());
    }

    private String getCustomerRequest() throws JsonProcessingException {
        CustomerRequest request = easyRandom.nextObject(CustomerRequest.class);
        request.setPassword("12345678");
        request.setGender("F");
        request.setPhone("1234567");
        request.setBirthDate("2050-12-31");

        return objectMapper.writeValueAsString(request);
    }
}
