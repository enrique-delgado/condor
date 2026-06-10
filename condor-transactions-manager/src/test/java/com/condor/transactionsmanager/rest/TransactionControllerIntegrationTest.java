package com.condor.transactionsmanager.rest;

import com.condor.transactionsmanager.dto.rest.TransactionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, topics = { "test-topic" })
class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @BeforeEach
    void setup() {
        String bootstrapServers = embeddedKafkaBroker.getBrokersAsString();
        System.setProperty("spring.kafka.bootstrap-servers", bootstrapServers);
    }

    @Test
    void testGetTransactionById() throws Exception {
        mockMvc.perform(get("/transactions/tx-1001"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateTransaction() throws Exception {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(1L);
        request.setAmount(50.0);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
}
