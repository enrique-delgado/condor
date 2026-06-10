package com.condor.transactionsmanager.rest;

import com.condor.transactionsmanager.mapper.AccountRestMapper;
import com.condor.transactionsmanager.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService service;

    @MockBean
    private AccountRestMapper mapper;

    @Test
    void testGetAccountById() throws Exception {
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk());
    }
}
