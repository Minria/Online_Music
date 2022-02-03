package servlet.music;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import entity.Music;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindMusicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("喜欢的音乐");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        System.out.println("测试查找函数");
        String str=req.getParameter("musicName");
        System.out.println("查询字符串->"+str);
        List<Music> list = null;
        if(str==null){
            list = MusicDao.findAllMusic();
        }else{
            list = MusicDao.ifMusic(str);
        }
        System.out.println("查询结果集");
        for(Music music:list){
            System.out.println(music);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),list);
    }
}
