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

public class RemoveLoveVideoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String strId=req.getParameter("id");
        int id=Integer.parseInt(strId);
        User user= (User) req.getSession().getAttribute("user");
        Map<String,Object> map=new HashMap<>();
        boolean successful= CollectionMVDao.removeLoveVideo(id,user.getId());
        if(successful){
            map.put("msg",true);
        }else{
            map.put("msg",false);
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}
