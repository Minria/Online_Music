package servlet.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CollectionMVDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoveVideoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        Map<String,Object> map=new HashMap<>();
        String strId=req.getParameter("id");
        User user= (User) req.getSession().getAttribute("user");
        int mvId=Integer.getInteger(strId);
        System.out.println("id->"+mvId);
        boolean isExit= CollectionMVDao.findLoveVideoById(mvId, user.getId());
        if(isExit){
            map.put("msg",false);
        }else{
            CollectionMVDao.addLoveVideo(mvId, user.getId());
            map.put("msg",true);
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}
