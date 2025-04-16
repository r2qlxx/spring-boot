package com.example.demo.domain.repository;

import com.example.demo.domain.object.User;

import java.util.List;
import java.util.Optional;

public interface RdsRepository {
    // CRUD.
    int create(User User);

    Optional<User> read(int userid);

    List<User> readAll();

    int update(User User);

    int delete(int userid);

    // For Security
    Optional<User> read(String username);
}
