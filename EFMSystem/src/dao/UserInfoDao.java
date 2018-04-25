package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


@Repository
public class UserInfoDao {

    // 测试用
    public void Test(UserInfo userInfo) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.save(userInfo);
        tx.commit();
        session.close();
    }

    // 返回所有用户
    @SuppressWarnings("unchecked")
    public List<UserInfo> users() {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from UserInfo");
        List<UserInfo> users = (List<UserInfo>) query.list();
        
        tx.commit();
        session.close();
        return users;
    }

    // 根据用户id返回一个用户
    public UserInfo getUserByID(Integer user_id) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        UserInfo userInfo = (UserInfo) session.get(UserInfo.class, user_id);

        tx.commit();
        session.close();
        return userInfo;
    }

    // 根据用户名返回一个用户
    public UserInfo getUserByName(String user_name) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from UserInfo where user_name = ?");
        query.setString(0, user_name);
        UserInfo userInfo = (UserInfo) query.uniqueResult();

        tx.commit();
        session.close();
        return userInfo;
    }

    // 根据用户id删除一个用户
    public void delUser(Integer userId) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        session.delete(getUserByID(userId));

        tx.commit();
        session.close();
    }

    // 注册一个新的用户
    public UserInfo register(UserInfo userInfo) {
        // 如果按用户名查询有结果,则返回false,注册失败
        if(getUserByName(userInfo.getUser_name()) != null)
            return null;

        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        userInfo.setDate(new Date());
        userInfo.setTactics(1);
        userInfo.setMoney(0.0);
        session.save(userInfo);
        
        tx.commit();
        session.close();
        return userInfo;
    }

    // 更新一个用户的信息
    public boolean update(UserInfo userInfo) {
        return false;
    }

    // 删除一个用户
    public boolean delUser(UserInfo userInfo) {
        return false;
    }

    // 基于id更新一个用户的信息
    public UserInfo update(Integer id, UserInfo userInfo) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        UserInfo ui = (UserInfo)session.get(UserInfo.class, id);
        ui.setUser_id(userInfo.getUser_id());
        ui.setUser_name(userInfo.getUser_name());
        ui.setPassword(userInfo.getPassword());
        ui.setAge(userInfo.getAge());
        ui.setAddress(userInfo.getAddress());
        ui.setTactics(userInfo.getTactics());
        ui.setMoney(userInfo.getMoney());
        
        tx.commit();
        session.close();
        return ui;
    }

    // 基于用户id修改用户密码
    public boolean rePwd(Integer user_id, String pwd) {

            return false;
    }

}
