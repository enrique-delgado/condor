package com.condor.customersmanager.mapper;

import com.condor.customersmanager.dto.rest.CustomerRequest;
import com.condor.customersmanager.dto.rest.CustomerResponse;
import com.condor.customersmanager.dto.service.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerRestMapper {


    /**
     * Convert a REST request DTO into a domain Customer.
     */
    Customer toDomain(CustomerRequest request);

    /**
     * Convert a domain Customer into a REST response DTO.
     */
    CustomerResponse toResponse(Customer customer);

    /**
     * Update an existing domain Customer from a REST request DTO.
     */
    void updateDomainFromRequest(CustomerRequest request, @MappingTarget Customer customer);
}
