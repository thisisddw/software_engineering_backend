package com.example.backend.dao;

import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("type")
            );
        }
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", new UserMapper());
    }

    public User getUserById(Long id) {
        List<User> query = jdbcTemplate.query("select * from user where id = ?",
                new UserMapper(), id);
        return query.isEmpty() ? null : query.get(0);
    }

    public User getUserByName(String name) {
        List<User> query = jdbcTemplate.query("select * from user where name = ?",
                new UserMapper(), name);
        return query.isEmpty() ? null : query.get(0);
    }

    public int addUser(User user) {
        return jdbcTemplate.update("insert into user(name,password,type) values(?,?,?)",
                user.getName(), user.getPassword(), user.getType());
    }
}
