package servlet.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CollectionMVDao;
import entity.User;
import entity.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindLoveVideoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("查找喜欢的视频");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String str=req.getParameter("loveVideoName");
        User user= (User) req.getSession().getAttribute("user");
        List<Video> list=null;
        if(str==null){
            list= CollectionMVDao.findLoveVideo(user.getId());
        }else{
            list = CollectionMVDao.ifVideoLove(str, user.getId());
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),list);
    }
}
