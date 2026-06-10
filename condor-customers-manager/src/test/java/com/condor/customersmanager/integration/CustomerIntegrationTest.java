package com.condor.customersmanager.integration;

import com.condor.commons.exception.GlobalExceptionHandler;
import com.condor.customersmanager.CustomersManagerApplication;
import com.condor.customersmanager.dto.rest.CustomerRequest;
import com.condor.customersmanager.util.RandomFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CustomersManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(GlobalExceptionHandler.class)
class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final EasyRandom easyRandom = RandomFactory.getEasyRandom();

    @Test
    void testGetAllCustomers() throws Exception {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerRequest request =  getCustomerRequest();
        String json =  objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(request.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(request.getLastName()));
    }

    @Test
    void testGetCustomerByIdNotFound() throws Exception {
        mockMvc.perform(get("/customers/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Customer with id 999 was not found"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        // primero creamos un cliente
        String createJson =  getCustomerRequestString();

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andExpect(status().isOk());

        // luego actualizamos
        CustomerRequest updateRequest =  getCustomerRequest();
        String updateJson =  objectMapper.writeValueAsString(updateRequest);

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(updateRequest.getFirstName()));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        // primero creamos un cliente
        String createJson = getCustomerRequestString();

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andExpect(status().isOk());

        // luego borramos
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());
    }

    private CustomerRequest getCustomerRequest() {
        CustomerRequest request = easyRandom.nextObject(CustomerRequest.class);
        request.setPassword("12345678");
        request.setGender("F");
        request.setPhone("1234567");
        request.setBirthDate("2050-12-31");

        return request;
    }

    private String getCustomerRequestString() throws JsonProcessingException {
        CustomerRequest request = getCustomerRequest();

        return objectMapper.writeValueAsString(request);
    }

}
