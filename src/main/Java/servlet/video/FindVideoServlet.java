package servlet.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.VideoDao;
import entity.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindVideoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("这是喜欢的视频");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String str=req.getParameter("videoName");
        System.out.println("查询字符串->"+str);
        List<Video> list= null;
        if(str==null){
            list= VideoDao.findAllVideo();
        }else {
            list=VideoDao.ifVideo(str);
        }
        System.out.println("查询结果集合");
        for(Video v:list){
            System.out.println(v);
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),list);
    }
}
