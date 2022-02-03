package servlet.music;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import entity.Music;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        System.out.println("删除指定音乐！");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        Map<String,Object> map=new HashMap<>();
        String strId=req.getParameter("id");
        int id=Integer.parseInt(strId);
        System.out.println("删除id->"+id);
        Music music= MusicDao.findMusicById(id);
        if(music==null){
            return;
        }else{
            boolean successful=MusicDao.deleteMusic(id);
            if(successful){
                String path="D:\\Github\\OnlineMusic\\target\\OnlineMusic\\"+music.getUrl()+".mp3";
                System.out.println("音乐路径->"+path);
                File file = new File(path);
                System.out.println("文件是否存在->"+file.exists());
                if(file.delete()){
                    map.put("msg",true);
                }else{
                    map.put("msg",false);
                    System.out.println("文件名："+file.getName());
                    System.out.println("删除文件失败！");
                }
            }else{
                map.put("msg",false);
            }
        }
        //将map转化为json
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}
