import dao.CollectionMVDao;
import entity.Video;

import java.util.List;

public class TestCollectionMVDao {
    public static void main(String[] args) {
        CollectionMVDao.removeLoveVideo(1,1);
        System.out.println(CollectionMVDao.findLoveVideoById(1, 1));
        CollectionMVDao.removeLoveVideo(1,2);
        System.out.println(CollectionMVDao.findLoveVideoById(1, 3));
    }
    public static void main4(String[] args) {
        System.out.println(CollectionMVDao.findLoveVideoById(1, 1));
    }
    public static void main3(String[] args) {
        List<Video> list=CollectionMVDao.ifVideoLove("test",1);
        for(Video v:list){
            System.out.println(v);
        }
    }
    public static void main2(String[] args) {
        List<Video> list=CollectionMVDao.findLoveVideo(1);
        for(Video v:list){
            System.out.println(v);
        }
    }
    public static void main1(String[] args) {
        System.out.println(CollectionMVDao.addLoveVideo(1, 1));
        System.out.println(CollectionMVDao.addLoveVideo(1, 2));
    }
}
