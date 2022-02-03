package servlet.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.VideoDao;
import entity.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DeleteVideoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("删除指定视频");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        Map<String,Object> map=new HashMap<>();
        String strId=req.getParameter("id");
        int id=Integer.parseInt(strId);
        System.out.println("id->"+id);
        Video video= VideoDao.findVideoById(id);
        if(video==null){
            return;
        }else{
            VideoDao.deleteVideo(id);
            String path="D:\\Github\\OnlineMusic\\target\\OnlineMusic\\"+video.getUrl()+".mp4";
            File file=new File(path);
            if(file.delete()){
                System.out.println("删除成功");
                map.put("msg",true);
            }else{
                System.out.println("删除失败");
                map.put("msg",false);
            }
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),mapper);
    }
}
