package servlet.music;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CollectionDao;
import entity.Music;
import entity.User;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class FindLoveMusicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("查找喜欢的音乐");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String str=req.getParameter("loveMusicName");
        User user= (User) req.getSession().getAttribute("user");
        List<Music> list;
        if(str==null){
            list = CollectionDao.findLoveMusic(user.getId());
        }else{
            list = CollectionDao.ifMusicLove(str, user.getId());
        }
        for(Music music:list){
            System.out.println(music);
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),list);
    }

}
