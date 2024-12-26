package com.hf.dao.impl;

import com.hf.dao.UserDao;
import com.hf.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class UserDaoImpl implements UserDao {
    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    @Override
    public boolean isIdRepeated(String id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM surrender WHERE id = ?)";
        try {
            return runner.query(sql, new ScalarHandler<Long>(), id) == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isNameRepeated(String name) {
        String sql = "SELECT EXISTS(SELECT 1 FROM surrender WHERE name = ?)";
        try {
            return runner.query(sql, new ScalarHandler<Long>(), name) == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO surrender (id,name,time) VALUES (?,?,?)";
        Timestamp timestamp = new Timestamp(user.getTime().getTime());
        try {
            runner.update(sql, user.getId(), user.getName(), timestamp);
            user.destroy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User queryUserById(String id) {
        String sql = "SELECT id,name,time FROM surrender WHERE id = ? ";
        try {
            return runner.query(sql, rs -> {
                User user = new User();
                if (rs.next()) {
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setTime(new Date(rs.getTimestamp("time").getTime()));
                }
                return user;
            }, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
