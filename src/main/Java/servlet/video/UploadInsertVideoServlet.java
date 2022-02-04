package servlet.video;

import dao.VideoDao;
import entity.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadInsertVideoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String strings = (String)req.getSession().getAttribute("fileName");
        String[] titles = strings.split("\\.");
        String title = titles[0];
        System.out.println("title:" + title);
        String url = "video\\"+title;
        System.out.println("url："+url);
        String author = req.getParameter("author");
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(new Date());
        VideoDao.addVideo(title,author,time,url,userId);
        resp.sendRedirect("list.html");
    }
}
