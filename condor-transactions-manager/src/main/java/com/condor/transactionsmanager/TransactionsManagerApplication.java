package com.condor.transactionsmanager;

import com.condor.commons.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GlobalExceptionHandler.class)
public class TransactionsManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionsManagerApplication.class, args);
    }
}
