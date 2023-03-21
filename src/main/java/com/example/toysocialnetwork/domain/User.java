package com.example.toysocialnetwork.domain;

import java.util.Objects;


public class User extends Entity<Long>{
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    private final String salt;


    public User(String firstname, String lastname, String email, String password, String salt) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }



    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(),getLastname(),getEmail(),getPassword());
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User that)) return false;
        return (getFirstname().equals(that.getFirstname()) &&
            getLastname().equals(that.getLastname()) &&
            getEmail().equals(that.getEmail()) &&
            getPassword().equals(that.getPassword()));
    }

    @Override
    public String toString() {
        return "Id: " + getId() + " firstname: " + firstname + " lastname: " + lastname + " email: " + email + "\n";
    }
}
