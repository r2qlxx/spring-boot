package com.example.demo.infrastructure.repository;

import com.example.demo.domain.object.User;
import com.example.demo.domain.repository.RdsRepository;
import com.example.demo.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RdsRepositoryImpl implements RdsRepository {
    private final JdbcTemplate jdbc;

    @Override
    public int create(User user) {
        try {
            return jdbc.update(SqlQuery.CREATE_USER, user.getId(), user.getName(), user.getBirthday());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> read(int id) {
        RowMapper<UserEntity> rowMapper = new BeanPropertyRowMapper<>(UserEntity.class);
        try {
            UserEntity userEntity = jdbc.queryForObject(SqlQuery.READ_USER_BY_ID, rowMapper, id);
            return Optional.ofNullable(userEntity).map(UserEntity::toUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> readAll() {
        RowMapper<UserEntity> rowMapper = new BeanPropertyRowMapper<>(UserEntity.class);
        try {
            return jdbc.query(SqlQuery.READ_ALL_USERS, rowMapper).stream().map(UserEntity::toUser).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        try {
            return jdbc.update(SqlQuery.UPDATE_USER, user.getName(), user.getBirthday(), user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            return jdbc.update(SqlQuery.DELETE_USER, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
