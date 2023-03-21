package com.example.toysocialnetwork.repository.memory;

import com.example.toysocialnetwork.domain.User;
import com.example.toysocialnetwork.domain.validators.Validator;


public class MemoryUserRepository extends MemoryRepository<Long, User> {
    public MemoryUserRepository(Validator<User> validator) {
        super(validator);
    }

    public Long getLowestFreeId() {
        for (Long i = 1L; ; ++i) {
            if (!entities.containsKey(i)) {
                return i;
            }
        }
    }
}
