package servlet.music;

import dao.MusicDao;
import entity.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UploadInsertMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String strings = (String)req.getSession().getAttribute("fileName");
        String[] titles = strings.split("\\.");
        String title = titles[0];
        System.out.println("title:" + title);
        String url = "music\\"+title;
        System.out.println("url："+url);
        String singer = req.getParameter("singer");
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(new Date());
        MusicDao.addMusic(title,singer,time,userId,url);
        resp.sendRedirect("list.html");
    }
}
