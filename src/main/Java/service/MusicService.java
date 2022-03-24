package service;

import dao.MusicDao;
import entity.Music;

import java.io.File;

public class MusicService {
    public static boolean deleteMusic(int id,int userId){
        Music music= MusicDao.findMusicById(id);
        if(music==null){
            return false;
        }else{
            if(music.getUserId()!=userId) {
                return false;
            }
            boolean successful=MusicDao.deleteMusic(id);
            if(successful){
                String path="/root/apache-tomcat-8.5.75/webapps/OnlineMusic/"+music.getUrl()+".mp3";
                System.out.println("音乐路径->"+path);
                File file = new File(path);
                System.out.println("文件是否存在->"+file.exists());
                if(file.delete()){
                    return true;
                }else{
                    System.out.println("文件名："+file.getName());
                    System.out.println("删除文件失败！");
                    return false;
                }
            }else{
                return false;
            }
        }
    }
}
