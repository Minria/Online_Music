package servlet.music;

import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


@MultipartConfig
public class UploadMusicServlet extends HttpServlet {
    private static final String URL="/root/apache-tomcat-8.5.75/webapps/OnlineMusic/music/";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            req.setAttribute("msg","登陆后上传");
        }else{
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items;
            try {
                items = upload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
                return;
            }
            System.out.println("items:"+items );
            FileItem item = items.get(0);
            System.out.println("item： "+item);
            String fileName = item.getName();
            System.out.println("fileName"+fileName);
            req.getSession().setAttribute("fileName", fileName);
            try {
                item.write(new File(URL, fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect("uploadsucess.html");
        }
    }
}
