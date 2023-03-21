package com.example.toysocialnetwork.repository;

import com.example.toysocialnetwork.domain.Entity;
import com.example.toysocialnetwork.domain.validators.ValidationException;

import java.util.List;


public interface Repository<ID, E extends Entity<ID>> {

    E findOne(ID id);

    List<E> findAll();

    E save(E entity);

    E delete(ID id);

    E update(E entity);
}
