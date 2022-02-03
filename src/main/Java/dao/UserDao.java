package dao;

import dbutil.DBUtil;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static boolean addUser(User user){
        Connection connection=DBUtil.getConnection();
        PreparedStatement statement=null;
        String sql="insert into user values (null,?,?,?,?,?)";
        try {
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setInt(3,user.getAge());
            statement.setString(4, user.getGender());
            statement.setString(5, user.getEmail());
            int ret = statement.executeUpdate();
            if(ret!=0){
                System.out.println("新增用户成功");
                return true;
            }
            System.out.println("新增用户失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
        return false;
    }
    public static User findUserByName(String username){
        Connection connection = DBUtil.getConnection();
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql="select * from user where username = ?";
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getInt("age"));
                user.setGender(resultSet.getString("gender"));
                user.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return user;
    }
}
