package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dao.tables.UserInfo;
import dao.util.UtilDao;
import dao.util.UtilFactory;

@Repository
public class UserInfoDao extends UtilDao<UserInfo> {

    public List<UserInfo> getAll() {
        return getAll("UserInfo");
    }

    public UserInfo getById(Integer id) {
        return getById("UserInfo", id);
    }
    
    public UserInfo update(Integer id, UserInfo newObj) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        UserInfo oldObj = (UserInfo) session.get(UserInfo.class, id);
        oldObj.setUserName(newObj.getUserName());
        oldObj.setPassword(newObj.getPassword());
        oldObj.setAddress(newObj.getAddress());
        oldObj.setTell(newObj.getTell());
        oldObj.setMoney(newObj.getMoney());
        oldObj.setRole(newObj.getRole());
        oldObj.setTactics(newObj.getTactics());
        oldObj.setIsUnusually(newObj.getIsUnusually());

        tx.commit();
        session.close();
        return oldObj;
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


    // 注册一个新的用户
    public UserInfo register(UserInfo userInfo) {
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

}
