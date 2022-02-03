package dao;

import dbutil.DBUtil;
import entity.Music;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDao {

    public static List<Music> ifMusic(String name){
        List<Music> list=new ArrayList<>();
        Connection connection= DBUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String sql="select * from music where title like ?";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            String newName="%"+name+"%";
            statement.setString(1,newName);
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

    public static Music findMusicById(int id){
        Connection connection= DBUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Music music=null;
        String sql="select * from music where id = ?";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                music=new Music();
                music.setId(id);
                music.setTitle(resultSet.getString("title"));
                music.setSinger(resultSet.getString("singer"));
                music.setTime(resultSet.getDate("time"));
                music.setUrl(resultSet.getString("url"));
                music.setUserId(resultSet.getInt("userid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return music;
    }

    /**
     * @effect 查找所有音乐
     * @return 储存到一个线性表中返回
     */
    public static List<Music> findAllMusic(){
        List<Music> list=new ArrayList<>();
        Connection connection= DBUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String sql="select * from music";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
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

    public static boolean deleteMusic(int id){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        String sql="delete from music where id = ?";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setInt(1,id);
            int ret= statement.executeUpdate();
            if(ret!=0){
                System.out.println("删除成功");
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


    public static void addMusic(String title,String singer,String time,int userId,String url){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        String sql="insert into music values (null,?,?,?,?,?)";
        try {
            assert connection != null;
            statement=connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2, singer);
            statement.setString(3, time);
            statement.setString(4, url);
            statement.setInt(5,userId);
            int ret=statement.executeUpdate();
            if(ret!=0){
                System.out.println("上传成功");
                return;
            }
            System.out.println("上传失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    public static void addMusic(Music music){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        String sql="insert into music values (null,?,?,?,?,?)";
        try {
            assert connection != null;
            statement=connection.prepareStatement(sql);
            statement.setString(1,music.getTitle());
            statement.setString(2, music.getSinger());
            statement.setDate(3, music.getTime());
            statement.setString(4, music.getUrl());
            statement.setInt(5,music.getUserId());
            int ret=statement.executeUpdate();
            if(ret!=0){
                System.out.println("上传成功");
                return;
            }
            System.out.println("上传失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

}
