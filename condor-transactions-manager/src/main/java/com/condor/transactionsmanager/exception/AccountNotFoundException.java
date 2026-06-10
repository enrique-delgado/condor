package com.condor.transactionsmanager.exception;

import com.condor.commons.exception.ItemNotFoundException;
import lombok.Getter;

@Getter
public class AccountNotFoundException extends ItemNotFoundException {

    public AccountNotFoundException(Long accountId) {
        super(accountId, "account.notfound");
    }
}

