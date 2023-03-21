package com.example.toysocialnetwork.repository.memory;



import com.example.toysocialnetwork.domain.Entity;
import com.example.toysocialnetwork.domain.validators.ArgumentException;
import com.example.toysocialnetwork.domain.validators.Validator;
import com.example.toysocialnetwork.repository.Repository;

import java.util.*;

public class MemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    protected Validator<E> validator;
    protected Map<ID, E> entities;

    public MemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities =  new HashMap<ID, E>();
    }

    @Override
    public E findOne(ID id) {
        if (id == null) {
            throw new ArgumentException("ID cannot be null");
        }
        if (entities.get(id) == null) {
            throw new ArgumentException("ID does not exist");
        }
        return entities.get(id);
    }

    @Override
    public List<E> findAll() {
        return entities.values().stream().toList();
    }

    @Override
    public E save(E entity) {
        if (entity == null) {
            throw new ArgumentException("Entity cannot be null");
        }
        validator.validate(entity);
        if (entities.get(entity.getId()) != null) {
            return entity;
        }
        entities.put(entity.getId(),entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        if (id == null) {
            throw new ArgumentException("ID cannot be null");
        }
        if (entities.get(id) == null) {
            return null;
        }
        E copyEnt = entities.get(id);
        entities.remove(id);
        return copyEnt;
    }

    @Override
    public E update(E entity) {
        if (entity == null) {
            throw new ArgumentException("Entity cannot be null");
        }
        if (entities.get(entity.getId()) == null) {
            return entity;
        }
        entities.replace(entity.getId(), entity);
        return null;
    }
}
