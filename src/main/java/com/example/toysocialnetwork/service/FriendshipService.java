package com.example.toysocialnetwork.service;

import com.example.toysocialnetwork.domain.Friendship;
import com.example.toysocialnetwork.domain.validators.ArgumentException;
import com.example.toysocialnetwork.repository.db.DBFriendshipRepository;

import java.time.LocalDate;
import java.util.*;

public class FriendshipService {
    private final DBFriendshipRepository repository;

    public List<Long> getRequests(Long id, String status) {
        return repository.getRequests(id, status);
    }

    public List<Long> getNotFriends(Long id, long maxSize) {
        return repository.getNotFriends(id, maxSize);
    }

    public FriendshipService(DBFriendshipRepository repository) {
        this.repository = repository;
    }

    public void addFriend(Long idUser, Long idNewFriend) {
        if (idUser == null || idNewFriend == null) {
            throw new ArgumentException("At least one id is invalid");
        }

        Friendship friendship = new Friendship(idUser,idNewFriend);
        friendship.setId(repository.getLowestFreeId());
        repository.save(friendship);
    }

    public void confirmFriends(Long id1, Long id2) {
        repository.confirm(id1,id2);
    }

    public void removeFriend(Long idUser, Long idNewFriend) {
        if (idUser == null || idNewFriend == null) {
            throw new ArgumentException("At least one id is invalid");
        }

        repository.delete(idUser, idNewFriend);
    }

    public String getFriendsFrom(Long id1, Long id2) {
        LocalDate ans1 = repository.getFriendsFrom(id1, id2);
        LocalDate ans2 = repository.getFriendsFrom(id2, id1);
        if (ans1 == null) {
            return ans2.toString();
        }
        return ans1.toString();
    }

    public List<Long> getFriendRequestsSent(Long id) {
        return repository.getFriendshipRequestsSent(id);
    }
}
