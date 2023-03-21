package com.example.toysocialnetwork.repository.memory;

import com.example.toysocialnetwork.domain.Friendship;
import com.example.toysocialnetwork.domain.validators.ArgumentException;
import com.example.toysocialnetwork.domain.validators.Validator;

import java.util.ArrayList;
import java.util.Map;

public class MemoryFriendshipRepository extends MemoryRepository<Long, Friendship> {
    public MemoryFriendshipRepository(Validator<Friendship> validator) {
        super(validator);
    }

    public Long getLowestFreeId() {
        for (Long i = 1L; ; ++i) {
            if (!entities.containsKey(i)) {
                return i;
            }
        }
    }

    public Long delete(Long id1, Long id2) {
        for (Friendship friendship : entities.values()) {
            if (Long.min(friendship.getId1(), friendship.getId2()) == Long.min(id1, id2) &&
                Long.max(friendship.getId1(), friendship.getId2()) == Long.max(id1,id2)) {
                entities.remove(friendship.getId());
                return friendship.getId();
            }
        }
        throw new ArgumentException("The friendship does not exists");
    }
}
