import dao.UserDao;
import entity.User;

public class TestUserDao {
    //按照名字查询用户测试，并对前面的测试进行验证
    public static void main(String[] args) {
        User user= UserDao.findUserByName("admin");
        System.out.println(user);
    }
    //新增用户测试
    public static void main1(String[] args) {
        User user=new User();
        user.setAge(10);
        user.setUserName("admin");
        user.setPassword("123");
        user.setGender("男");
        user.setEmail("810760487@qq.com");
        UserDao.addUser(user);
    }
}
