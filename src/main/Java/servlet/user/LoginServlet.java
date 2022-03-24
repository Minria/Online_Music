package servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


//登录请求处理
//请求体：username&&password
//返回体：msg：true



public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        System.out.println("账号->"+username);
        System.out.println("密码->"+password);
        User user= UserService.login(username,password);
        Map<String ,Object> returnMap = new HashMap<>();
        if(user==null){
            returnMap.put("msg",false);
            System.out.println("登录失败");
        }else{
            req.getSession().setAttribute("user",user);
            returnMap.put("msg",true);
            System.out.println("登录成功");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),returnMap);
    }
}
