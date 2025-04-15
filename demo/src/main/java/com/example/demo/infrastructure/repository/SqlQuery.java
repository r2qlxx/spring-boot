package com.example.demo.infrastructure.repository;

public class SqlQuery {

    public static final String CREATE_USER = """
            INSERT INTO users
            (id, name, birthday)
            VALUES (?, ?, ?)
            """;

    public static final String READ_USER_BY_ID = """
            SELECT * FROM users WHERE id = ?
            """;

    public static final String READ_ALL_USERS = """
            SELECT * FROM users
            """;

    public static final String UPDATE_USER = """
            UPDATE users
            SET name = ?,
                birthday = ?
            WHERE id = ?
            """;

    public static final String DELETE_USER = """
            DELETE FROM users WHERE id = ?
            """;
}
