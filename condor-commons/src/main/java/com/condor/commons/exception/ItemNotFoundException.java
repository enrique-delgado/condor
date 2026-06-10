package com.condor.commons.exception;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {

    private final Object id;
    private final String messageKey;

    public ItemNotFoundException(Object id, String messageKey) {
        super(messageKey); // guarda la key como mensaje provisional
        this.id = id;
        this.messageKey = messageKey;
    }
}
