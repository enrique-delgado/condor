package com.condor.transactionsmanager.mapper;

import com.condor.transactionsmanager.dto.service.Account;
import com.condor.transactionsmanager.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountServiceMapper {

    Account toService(AccountEntity entity);

    AccountEntity toEntity(Account account);
}

