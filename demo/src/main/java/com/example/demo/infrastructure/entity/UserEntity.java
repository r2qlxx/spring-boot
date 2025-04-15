package com.example.demo.infrastructure.entity;

import com.example.demo.domain.object.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
public class UserEntity {
    private int id;
    private String name;
    private LocalDate birthday;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User toUser() {
        return User.builder().id(id).name(name).birthday(birthday).build();
    }
}
