package com.example.demo.infrastructure.repository;

public class SqlQuery {

    public static final String CREATE_USER = """
            INSERT INTO users
            (userid, username, password, birthday, role)
            VALUES (?, ?, ?, ?, ?)
            """;

    public static final String READ_USER_BY_ID = """
            SELECT * FROM users WHERE userid = ?
            """;

    public static final String READ_ALL_USERS = """
            SELECT * FROM users
            """;

    public static final String UPDATE_USER = """
            UPDATE users
            SET username = ?,
                password = ?,
                birthday = ?,
                role = ?
            WHERE userid = ?
            """;

    public static final String DELETE_USER = """
            DELETE FROM users WHERE userid = ?
            """;

    // For Security
    public static final String READ_USER_BY_NAME = """
            SELECT * FROM users WHERE username = ? LIMIT 1;
            """;
}
