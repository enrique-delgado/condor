package com.condor.transactionsengine.dto.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WebhookNotification {
    private String id;
    private String status;
    private String errorMessage;
}
