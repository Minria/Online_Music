package dao;

import dbutil.DBUtil;
import entity.Music;
import entity.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectionMVDao {
    public static List<Video> ifVideoLove(String str, int userId) {
        List<Video> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select v.id as id,title,author,time,url,userid from collectionmv c,video v where c.video_id=v.id and user_id= ? and title like '%" + str + "%'";
        try {
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Video video = new Video();
                video.setId(resultSet.getInt("id"));
                video.setTitle(resultSet.getString("title"));
                video.setAuthor(resultSet.getString("author"));
                video.setTime(resultSet.getDate("time"));
                video.setUrl(resultSet.getString("url"));
                video.setUserId(resultSet.getInt("userid"));
                list.add(video);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return list;
    }
    public static List<Video> findLoveVideo(int userId){
        List<Video> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select v.id as id,title,author,time,url,userid from collectionmv c,video v where c.video_id = v.id and  c.user_id=?";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                Video video=new Video();
                video.setId(resultSet.getInt("id"));
                video.setTitle(resultSet.getString("title"));
                video.setAuthor(resultSet.getString("author"));
                video.setTime(resultSet.getDate("time"));
                video.setUrl(resultSet.getString("url"));
                video.setUserId(resultSet.getInt("userid"));
                list.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return list;
    }
    public static boolean findLoveVideoById(int videoId,int userId){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet= null;
        String sql = "select * from collectionmv where user_id = ? and video_id = ?";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,videoId);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean removeLoveVideo(int id,int userId){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        String sql="delete from collectionmv where video_id = ? and user_id = ?";
        try {
            assert connection != null;
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setInt(2,userId);
            int ret = statement.executeUpdate();
            if(ret!=0){
                System.out.println("移除喜欢音乐成功");
                return true;
            }
            System.out.println("移除喜欢音乐失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }


    public static boolean addLoveVideo(int id,int userId){
        Connection connection= DBUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "insert into collectionmv values (null,?,?)";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setInt(2,userId);
            int ret = statement.executeUpdate();
            if(ret!=0){
                System.out.println("添加喜欢的音乐成功");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }
}
