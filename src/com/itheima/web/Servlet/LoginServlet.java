package com.itheima.web.Servlet;

import com.itheima.domain.User;
import com.itheima.dao.UserDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet( "/loginCheck")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("utf-8");
       /* String username=request.getParameter("username");
        String password=request.getParameter("password");*/
        User user=new User();
        /*user.setUsername(username);
        user.setPassword(password);*/
        UserDao dao=new UserDao();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User userCheck= dao.login(user);
        if(userCheck!=null){
           /* response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登录成功！用户名,欢迎您");*/
           request.setAttribute("user",user);

            try {
                request.getRequestDispatcher("successServlet").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }

        }else {
          /*  response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登录失败，用户名或密码错误");*/
            try {
                request.getRequestDispatcher("failServle").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
       /* System.out.println(username);
        System.out.println(password);
        System.out.println(user);*/

}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }


}
