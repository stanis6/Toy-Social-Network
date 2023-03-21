package com.example.toysocialnetwork.domain.validators;

import com.example.toysocialnetwork.domain.Message;

public class MessageValidator implements  Validator<Message> {

    @Override
    public void validate(Message entity) throws ValidationException {
        String err = "";

        if (entity.getMessage().equals("")) {
            err += "The message cannot be empty";
        }

        if (!err.equals("")) {
            throw new ValidationException(err);
        }
    }
}
