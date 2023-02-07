package com.uom.seat.user.validator;



import com.uom.seat.user.dto.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public void validateUser(UserRequest user) {
        validateMandotoryFields(user);
        validateOptionalFields(user);
    }

    private void validateMandotoryFields(UserRequest user) {
        // TODO

    }

    private void validateOptionalFields(UserRequest user) {
        // TODO
    }


}
