package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDao {
    public void Test(UserInfo userInfo) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.save(userInfo);
        tx.commit();
        session.close();
    }
}
