import dao.VideoDao;
import entity.Video;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestVideoDao {
    public static void main(String[] args) {
        System.out.println(VideoDao.deleteVideo(4));
    }
    public static void main4(String[] args) {
        List<Video> list=VideoDao.ifVideo("test");
        for(Video video:list){
            System.out.println(video);
        }
    }
    public static void main3(String[] args) {
        Video video=VideoDao.findVideoById(1);
        System.out.println(video);
    }

    public static void main2(String[] args) {
        List<Video> list=VideoDao.findAllVideo();
        for(Video video:list){
            System.out.println(video);
        }
    }
    public static void main1(String[] args) {
        Video video=new Video();

//        video.setId(1);
//        video.setTitle("test1");
//        video.setAuthor("wfm");
//        video.setUrl("video/test1");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        String time=sdf.format(new Date());
//        video.setUserId(1);



//        video.setId(1);
//        video.setTitle("test2");
//        video.setAuthor("wfm");
//        video.setUrl("video/test2");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        String time=sdf.format(new Date());
//        video.setUserId(1);


//        video.setId(1);
//        video.setTitle("test3");
//        video.setAuthor("wfm");
//        video.setUrl("video/test3");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        String time=sdf.format(new Date());
//        video.setUserId(1);

//        VideoDao.addVideo(video.getTitle(),video.getAuthor(),time,video.getUrl(),video.getUserId());
    }
}
