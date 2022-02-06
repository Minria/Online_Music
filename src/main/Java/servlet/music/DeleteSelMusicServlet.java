package servlet.music;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import entity.Music;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DeleteSelMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        String[] values = req.getParameterValues("id[]");
        System.out.println("deleteSelectedServlet："+ Arrays.toString(values));
        //删除
        int sum=0;
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<values.length;i++){
            int j = Integer.parseInt(values[i]);
            System.out.println("j :"+j);
            //调用Service层方法删除
            Music music = MusicDao.findMusicById(j);
            boolean successful = MusicDao.deleteMusic(j);
            //sum=sum+delete;
            if(successful) {
                //3、数据库删除完成后，检查还是否存在。如果不存在，那么删除掉磁盘上的文件
                File file = new File("/root/apache-tomcat-8.5.75/webapps/OnlineMusic/"+music.getUrl() + ".mp3");
                System.out.println("文件是否存在：" + file.exists());
                System.out.println("file: " + file);
                if (file.delete()) {
                    //证明删除成功
                    //map.put("msg", true);
                    sum++;
                } else {
                    //map.put("msg", false);
                    System.out.println("文件名：" + file.getName());
                    System.out.println("删除文件失败！");
                }
            }
        }
        System.out.println("sum: "+sum);
        //sum==values.length 说明选中的所有元素已经全部删除了
        if(sum==values.length){
            //证明删除成功
            map.put("msg",true);
        }else {
            map.put("msg",false);
        }
        //将map转化为json
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}
