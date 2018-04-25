package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dao.tables.UserInfo;
import dao.util.UtilFactory;

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
    public List<UserInfo> getAllUsers() {
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
    public UserInfo getUserByName(String userName) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from UserInfo where userName = ?");
        query.setString(0, userName);
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
        if (getUserByName(userInfo.getUserName()) != null)
            return null;

        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        userInfo.setId(null);
        if (userInfo.getTactics() == null)
            userInfo.setTactics(1);
        if (userInfo.getMoney() == null)
            userInfo.setMoney(0.0);
        if (userInfo.getIsUnusually() == null)
            userInfo.setIsUnusually(0);
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
    public UserInfo update(Integer id, UserInfo newUi) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        UserInfo oldUi = (UserInfo) session.get(UserInfo.class, id);
        oldUi.setUserName(newUi.getUserName());
        oldUi.setPassword(newUi.getPassword());
        oldUi.setAddress(newUi.getAddress());
        oldUi.setTell(newUi.getTell());
        oldUi.setMoney(newUi.getMoney());
        oldUi.setRole(newUi.getRole());
        oldUi.setTactics(newUi.getTactics());
        oldUi.setIsUnusually(newUi.getIsUnusually());

        tx.commit();
        session.close();
        return oldUi;
    }

    // 基于用户id修改用户密码
    public boolean rePwd(Integer userId, String pwd) {

        return false;
    }


}
