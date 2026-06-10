package com.condor.customersmanager.mapper;

import com.condor.customersmanager.dto.service.Customer;
import com.condor.customersmanager.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerServiceMapper {

    /**
     * Convert a domain Customer into a JPA entity.
     */
    CustomerEntity toEntity(Customer customer);

    /**
     * Convert a JPA entity into a domain Customer.
     */
    Customer toDomain(CustomerEntity entity);
}
