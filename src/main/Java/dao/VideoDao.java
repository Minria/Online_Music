package dao;

import dbutil.DBUtil;
import entity.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDao {

    public static List<Video> ifVideo(String str){
        List<Video> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from video where title like ?";
        try {
            assert connection != null;
            statement=connection.prepareStatement(sql);
            String name="%"+str+"%";
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
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
    public static Video findVideoById(int id){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String sql="select * from video where id = ?";
        try {
            assert connection != null;
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                Video video=new Video();
                video.setId(resultSet.getInt("id"));
                video.setTitle(resultSet.getString("title"));
                video.setAuthor(resultSet.getString("author"));
                video.setTime(resultSet.getDate("time"));
                video.setUrl(resultSet.getString("url"));
                video.setUserId(resultSet.getInt("userid"));
                return  video;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    public static List<Video> findAllVideo(){
        List<Video> list=new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from video";
        try {
            assert connection != null;
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Video video=new Video();
                video.setAuthor(resultSet.getString("author"));
                video.setTime(resultSet.getDate("time"));
                video.setId(resultSet.getInt("id"));
                video.setUrl(resultSet.getString("url"));
                video.setTitle(resultSet.getString("title"));
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
    public static boolean deleteVideo(int id){
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "delete from video where id = ?";
        try {
            assert connection != null;
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            int ret = statement.executeUpdate();
            if(ret!=0){
                System.out.println("删除视频成功");
                return true;
            }
            System.out.println("删除视频失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }
    public static boolean addVideo(String title,String author,String time,String url,int userId){
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "insert into video values (null,?,?,?,?,?)";
        try {
            assert connection != null;
            statement= connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,author);
            statement.setString(3,time);
            statement.setString(4,url);
            statement.setInt(5,userId);
            int ret = statement.executeUpdate();
            if(ret!=0){
                System.out.println("视频添加成功");
                return true;
            }
            System.out.println("视频添加失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }
}
