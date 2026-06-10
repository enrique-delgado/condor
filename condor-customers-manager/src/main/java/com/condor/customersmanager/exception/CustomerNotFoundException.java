package com.condor.customersmanager.exception;

import com.condor.commons.exception.ItemNotFoundException;
import lombok.Getter;

@Getter
public class CustomerNotFoundException extends ItemNotFoundException {

    public CustomerNotFoundException(Long customerId) {
        super(customerId, "customer.notfound");
    }

}
