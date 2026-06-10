package com.condor.transactionsmanager.rest;

import com.condor.commons.exception.GlobalExceptionHandler;
import com.condor.transactionsmanager.RandomFactory;
import com.condor.transactionsmanager.dto.rest.AccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, topics = { "test-topic" })
@Import(GlobalExceptionHandler.class)
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final EasyRandom easyRandom = RandomFactory.getEasyRandom();

    @Test
    void testGetAccountById() throws Exception {
        mockMvc.perform(get("/accounts/12345"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateAccount() throws Exception {
        AccountRequest request = easyRandom.nextObject(AccountRequest.class);
        request.setType("SAVINGS");
        request.setBalance(200.0);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("SAVINGS"))
                .andExpect(jsonPath("$.balance").value(200.0));
    }
}
