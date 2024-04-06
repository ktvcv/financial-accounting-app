package com.example.financialaccountingapp.repository;

import com.example.financialaccountingapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcTemplate;

    public long saveUser(final User user){
        final String sql = "INSERT INTO users(id) VALUES (:id)";
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id", user.getId());
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, parameters, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public boolean isUserIsNew(final String username){
        final String sql = "SELECT EXISTS(SELECT id FROM users WHERE username = :username)";
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("username", username);
        return jdbcTemplate.queryForObject(sql, parameters, Boolean.class);
    }

    public User fetchUserByUserName(final String username){
        String sql = "SELECT id, username, password, FROM users WHERE username = :username";
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("username", username);
        return jdbcTemplate.queryForObject(sql, parameters,
            (rs, rowNum) -> new User().setId(rs.getLong("id")));
    }

    //TODO: batch operations
}
