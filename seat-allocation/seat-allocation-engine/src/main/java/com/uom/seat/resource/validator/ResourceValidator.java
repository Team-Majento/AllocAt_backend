package com.uom.seat.resource.validator;

import com.uom.seat.resource.dto.ResourceRequest;
import org.springframework.stereotype.Component;

@Component
public class ResourceValidator {

    public void validateResource(ResourceRequest resource) {
        validateMandatoryFields(resource);
        validateOptionalFields(resource);
    }

    private void validateMandatoryFields(ResourceRequest resource) {
        // TODO

    }

    private void validateOptionalFields(ResourceRequest resource) {
        // TODO
    }



}
