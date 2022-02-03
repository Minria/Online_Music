
import dao.MusicDao;
import entity.Music;

import java.util.List;

public class TestMusicDao {
    public static void main5(String[] args) {
        MusicDao.deleteMusic(4);
    }

    public static void main4(String[] args) {
        List<Music> list=MusicDao.ifMusic("月");
        for(Music music:list){
            System.out.println(music);
        }
    }
    public static void main3(String[] args) {
        Music music=MusicDao.findMusicById(10);
        System.out.println(music);
    }
    public static void main2(String[] args) {
        //出现bug，因为时间格式问题，修改getTime为getDate
        List<Music> list=MusicDao.findAllMusic();
        for(Music music:list){
            System.out.println(music);
        }
    }
    public static void main1(String[] args) {
        Music music=new Music();


//        music.setTitle("谁会记得");
//        music.setUserId(1);
//        music.setSinger("六哲");
//        music.setTime(new Date(20200130));
//        music.setUrl("test/谁会记得");


//        music.setTitle("与共和国同行");
//        music.setUserId(1);
//        music.setSinger("西电");
//        music.setTime(new java.sql.Date(System.currentTimeMillis()));
//        music.setUrl("test/与共和国同行");

//        music.setTitle("星月神话");
//        music.setUserId(1);
//        music.setSinger("金莎");
//        music.setTime(new java.sql.Date(System.currentTimeMillis()));
//        music.setUrl("test/星月神话");


//        music.setTitle("一吻天荒");
//        music.setUserId(1);
//        music.setSinger("胡歌");
//        music.setTime(new java.sql.Date(System.currentTimeMillis()));
//        music.setUrl("test/一吻天荒");

//        music.setTitle("月亮之上");
//        music.setUserId(1);
//        music.setSinger("凤凰传奇");
//        music.setTime(new java.sql.Date(System.currentTimeMillis()));
//        music.setUrl("test/凤凰传奇");


        MusicDao.addMusic(music);
    }
}
