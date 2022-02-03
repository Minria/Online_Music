package dao;

import dbutil.DBUtil;
import entity.Music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectionDao {
    public static List<Music> ifMusicLove(String str, int userId) {
        List<Music> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select m.id as id,title,singer,time,url,userid from collection c,music m where c.music_id=m.id and user_id= ? and title like '%" + str + "%'";
        try {
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Music music = new Music();
                music.setId(resultSet.getInt("id"));
                music.setTitle(resultSet.getString("title"));
                music.setSinger(resultSet.getString("singer"));
                music.setTime(resultSet.getDate("time"));
                music.setUrl(resultSet.getString("url"));
                music.setUserId(resultSet.getInt("userid"));
                list.add(music);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return list;
    }
    public static List<Music> findLoveMusic(int userId){
        Connection connection=DBUtil.getConnection();
        List<Music> list=new ArrayList<>();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String sql="select m.id as id,title,singer,time,url,userid from collection c,music m where c.music_id=m.id and user_id=?";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Music music=new Music();
                music.setId(resultSet.getInt("id"));
                music.setTitle(resultSet.getString("title"));
                music.setSinger(resultSet.getString("singer"));
                music.setTime(resultSet.getDate("time"));
                music.setUrl(resultSet.getString("url"));
                music.setUserId(resultSet.getInt("userid"));
                list.add(music);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return list;
    }
    public static boolean findLoveMusicById(int userId,int musicId){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String sql="select * from collection where user_id = ? and music_id = ?";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,musicId);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return false;
    }

    public static boolean removeLoveMusic(int userId,int musicId){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        String sql="delete from collection where user_id = ? and music_id = ?";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,musicId);
            int ret=statement.executeUpdate();
            if(ret!=0){
                System.out.println("删除喜欢音乐成功");
                return true;
            }
            System.out.println("删除失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }

    public static boolean addLoveMusic(int userId,int musicId){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        String sql="insert into collection values (null,?,?)";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,musicId);
            int ret=statement.executeUpdate();
            if(ret!=0){
                System.out.println("添加喜欢音乐成功");
                return true;
            }
            System.out.println("添加失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }
}
