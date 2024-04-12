package com.example.financialaccountingapp.repository;

import com.example.financialaccountingapp.model.User;
import com.example.financialaccountingapp.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Repository
public class AuthRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcTemplate;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public long saveUser(final User user){
        final String sql = "INSERT INTO users(id, username, role," +
            " enabled, password, created_at, updated_at) " +
            "VALUES (:id, :username, :role, :enabled, :password, :createdAt, :updatedAt)";
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id", user.getId())
            .addValue("username", user.getUsername())
            .addValue("password", user.getPassword())
            .addValue("role", user.getRole().name())
            .addValue("createdAt", user.getCreatedAt())
            .addValue("updatedAt", user.getUpdatedAt())
            .addValue("enabled", user.isEnabled());
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

    public Optional<User> fetchUserByUserName(final String username){
        String sql = "SELECT id, username, password, role, first_name, last_name," +
            " enabled, created_at, updated_at FROM users WHERE username = :username";
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("username", username);
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, parameters,
            (rs, rowNum) ->
                new User()
                    .setId(rs.getLong("id"))
                    .setUsername(rs.getString("username"))
//                    .setUsername(rs.getString("username"))
//                    .setUsername(rs.getString("username"))
                    .setPassword(rs.getString("password"))
                    .setRole(UserRole.valueOf(rs.getString("role")))
                    .setEnabled(rs.getBoolean("enabled"))
                    .setCreatedAt(LocalDateTime.parse(rs.getString("created_at"), dateFormatter))
                    .setUpdatedAt(LocalDateTime.parse(rs.getString("updated_at"), dateFormatter))
            )

        );
    }

    //TODO: batch operations
}
