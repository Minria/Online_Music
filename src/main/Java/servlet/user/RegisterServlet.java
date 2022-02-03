package servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        Map<String,Object> map=new HashMap<>();
        if(username==null||password==null||username.length()==0||password.length()==0){
            System.out.println("账号或者密码为空");
            map.put("msg","账号或者密码为空");
        }else{
            String age=req.getParameter("age");
            String gender=req.getParameter("gender");
            String email=req.getParameter("email");
            User user=new User();
            user.setEmail(email);
            user.setGender(gender);
            user.setUserName(username);
            user.setPassword(password);
            user.setAge(Integer.parseInt(age));
            boolean sucess=UserService.register(user);
            if(sucess){
                map.put("msg","用户名已经存在or其他原因导致");
            }else{
                req.getSession().setAttribute("user",user);
                map.put("msg","注册成功");
            }
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}
