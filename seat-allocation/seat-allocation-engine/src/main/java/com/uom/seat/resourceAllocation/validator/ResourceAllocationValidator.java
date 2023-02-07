package com.uom.seat.resourceAllocation.validator;


import com.uom.seat.resourceAllocation.dto.ResourceAllocationRequest;
import org.springframework.stereotype.Component;

@Component
public class ResourceAllocationValidator {
    public void validateResourceAllocation(ResourceAllocationRequest resourceAlllocation) {
        validateMandotoryFields(resourceAlllocation);
        validateOptionalFields(resourceAlllocation);
    }
    private void validateMandotoryFields(ResourceAllocationRequest resourceAlllocation) {
        // TODO

    }

    private void validateOptionalFields(ResourceAllocationRequest resourceAlllocation) {
        // TODO
    }


}
