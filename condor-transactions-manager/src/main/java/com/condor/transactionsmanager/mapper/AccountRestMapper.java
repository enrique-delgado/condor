package com.condor.transactionsmanager.mapper;

import com.condor.transactionsmanager.dto.rest.AccountRequest;
import com.condor.transactionsmanager.dto.rest.AccountResponse;
import com.condor.transactionsmanager.dto.service.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountRestMapper {
    Account toService(AccountRequest request);
    AccountResponse toResponse(Account account);
}
