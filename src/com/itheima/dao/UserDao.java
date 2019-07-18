package com.itheima.dao;


import com.itheima.domain.User;
import com.itheima.Utils.DruidJDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
private JdbcTemplate template = new JdbcTemplate(DruidJDBCUtils.getDataSource());
    public User login(User user)  {
            String sql = "select * from user where username =? and password=?";
        User usercheck = null;
        try {
            usercheck = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
            return usercheck;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
