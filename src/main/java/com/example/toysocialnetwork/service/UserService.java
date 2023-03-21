package com.example.toysocialnetwork.service;


import com.example.toysocialnetwork.domain.User;
import com.example.toysocialnetwork.domain.validators.ArgumentException;
import com.example.toysocialnetwork.repository.db.DBUserRepository;
import com.example.toysocialnetwork.utils.Secure;

import java.util.List;

/**
 * Controller for the operations
 */
public class UserService {
    private final DBUserRepository repository;

    public UserService(DBUserRepository repository) {
        this.repository = repository;
    }

    public User findOne(Long id) {
        return repository.findOne(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void add(String firstname, String lastname, String email, String password) {
        byte[] salt = Secure.getSalt();
        String stringSalt = Secure.fromByteToStringHex(salt);
        String hashedPass = Secure.getHashedPassword(password, salt);
        User user = new User(firstname, lastname, email, hashedPass, stringSalt);
        user.setId(repository.getLowestFreeId());
        if (repository.save(user) != null) {
            throw new ArgumentException("Id already taken");
        }
    }

    public User findByEmailPassword(String email, String password) {
        String stringSalt = repository.getSaltByEmail(email);

        if (stringSalt == null) {
            return null;
        }

        byte[] salt = Secure.getSaltFromHex(stringSalt);
        String hashPass = Secure.getHashedPassword(password, salt);
        return repository.findByEmailPassword(email, hashPass);
    }

    public void delete(Long id) {
        if (repository.delete(id) == null) {
            throw new ArgumentException("ID cannot be null");
        }
    }

    public void update(Long id, String firstname, String lastname, String email, String password) {
        User user = new User(firstname,lastname,email,password, repository.findOne(id).getSalt());
        user.setId(id);
        repository.update(user);
    }

}