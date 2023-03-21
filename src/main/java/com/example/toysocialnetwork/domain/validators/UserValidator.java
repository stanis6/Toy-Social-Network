package com.example.toysocialnetwork.domain.validators;

import com.example.toysocialnetwork.domain.User;

import java.util.regex.Pattern;


public class UserValidator implements Validator<User>{

    @Override
    public void validate(User entity) throws ValidationException {
        String err = "";
        if (entity.getFirstname().equals("")) {
            err += "Firstname can't be empty\n";
        }

        if (entity.getLastname().equals("")) {
            err += "Lastname can't be empty\n";
        }

        if (!Pattern.matches("^[a-z0-9]+[._]?[a-z0-9]+@\\w+[.]\\w{2,3}$", entity.getEmail())) {
            err += "Email is not valid\n";
        }

        if (!err.equals("")) {
            throw new ValidationException(err);
        }
    }
}
