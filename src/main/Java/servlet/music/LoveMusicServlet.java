package servlet.music;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CollectionDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoveMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String strId=req.getParameter("id");
        User user= (User) req.getSession().getAttribute("user");
        int musicId=Integer.parseInt(strId);
        Map<String,Object> map=new HashMap<>();
        boolean isExit= CollectionDao.findLoveMusicById(user.getId(),musicId);
        if(isExit){
            map.put("msg",false);
        }else{
            boolean flag=CollectionDao.addLoveMusic(user.getId(),musicId);
            map.put("msg",flag);
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}
