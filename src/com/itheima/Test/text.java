package com.itheima.Test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.Test;

public class text {
    @Test
    public void login(){
        User user=new User();
        user.setUsername("zhangsan");
        user.setPassword("123");
        User userCheck=new UserDao().login(user);
        System.out.println(userCheck.getUsername()+","+userCheck.getPassword());
    }
}
