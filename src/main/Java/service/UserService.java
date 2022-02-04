package service;

import dao.UserDao;
import entity.User;


public class UserService {

    
    public static boolean register(User user){
        String name= user.getUserName();
        User findUser=UserDao.findUserByName(name);
        if(findUser==null){
            return UserDao.addUser(user);
        }else{
            return false;
        }
    }

    /**
     * @effect 用于登录
     * @param username 用户名
     * @param password 用户密码
     * @return 成功返回一个实例，失败返回null
     * @attention 账号密码不能为空指针或者空字符串
     */
    public static User login(String username, String password) {
        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            System.out.println("账号或者密码为空");
            return null;
        }
        User user = UserDao.findUserByName(username);
        if (user == null || !user.getUserName().equals(username) || !user.getPassword().equals(password)) {
            System.out.println("账号或者密码错误");
            return null;
        }
        return user;
    }
}
