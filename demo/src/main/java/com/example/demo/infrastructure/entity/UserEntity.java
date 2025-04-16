package com.example.demo.infrastructure.entity;

import com.example.demo.domain.object.User;
import com.example.demo.domain.object.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
public class UserEntity {
    private int userid;
    private String username;
    private String password;
    private LocalDate birthday;
    private UserRole role;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User toUser() {
        return User.builder().userid(userid).username(username).password(password).birthday(birthday).role(role).build();
    }
}
