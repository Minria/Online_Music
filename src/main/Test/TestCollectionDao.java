import dao.CollectionDao;
import entity.Music;

import java.util.List;

public class TestCollectionDao {
    public static void main(String[] args) {
        CollectionDao.removeLoveMusic(1, 1);
        boolean test = CollectionDao.findLoveMusicById(1, 1);
        System.out.println(test);
    }
    public static void main4(String[] args) {
        boolean test=CollectionDao.findLoveMusicById(1,2);
        System.out.println(test);
    }
    public static void main3(String[] args) {
        List<Music> list=CollectionDao.ifMusicLove("月",1);
        for(Music music:list){
            System.out.println(music);
        }
    }
    public static void main2(String[] args) {
        List<Music> list=CollectionDao.findLoveMusic(1);
        for(Music music:list){
            System.out.println(music);
        }
    }
    public static void main1(String[] args) {
        CollectionDao.addLoveMusic(1, 1);
        CollectionDao.addLoveMusic(1, 2);
        CollectionDao.addLoveMusic(1, 3);
        CollectionDao.addLoveMusic(1, 4);
        CollectionDao.addLoveMusic(1, 5);
    }
}
