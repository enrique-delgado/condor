package com.condor.transactionsmanager.exception;

import com.condor.commons.exception.ItemNotFoundException;
import lombok.Getter;

@Getter
public class TransactionNotFoundException extends ItemNotFoundException {

    public TransactionNotFoundException(String transactionId) {
        super(transactionId, "transaction.notfound");
    }
}
