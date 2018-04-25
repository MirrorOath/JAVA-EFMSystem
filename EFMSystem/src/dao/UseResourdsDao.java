package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dao.tables.UseRecords;
import dao.util.UtilFactory;

@Repository
public class UseResourdsDao {

    // 添加一条抄表记录
    public UseRecords addRecord(UseRecords useResource) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        session.save(useResource);

        tx.commit();
        session.close();
        return useResource;
    }

    // 根据date和userId返回一条抄表记录
    public UseRecords getRecordsByDateAndUserId(String date, Integer userId) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from UseRecords where userId = ? and date = ?");
        query.setString(0, userId.toString());
        query.setString(1, date);
//        System.out.println(query + "\tuserId=" + userId + "\tdate=" + date);
        UseRecords record = (UseRecords) query.uniqueResult();

        tx.commit();
        session.close();
        return record;
    }

    // 根据用户id返回所有抄表记录
    @SuppressWarnings("unchecked")
    public List<UseRecords> getRecordsByUserId(Integer userId) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from UseRecords where userId = ?");
        query.setString(0, userId.toString());
        List<UseRecords> Records = (List<UseRecords>) query.list();

        tx.commit();
        session.close();
        return Records;
    }

    @SuppressWarnings("unchecked")
    public List<UseRecords> getAllMeters() {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from UseRecords");
        List<UseRecords> Records = (List<UseRecords>) query.list();

        tx.commit();
        session.close();
        return Records;
    }

    public UseRecords update(Integer id, UseRecords newUr) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        UseRecords oldUr = (UseRecords) session.get(UseRecords.class, id);
        oldUr.setCurUsed(newUr.getCurUsed());
        oldUr.setUserId(newUr.getUserId());
        oldUr.setDate(newUr.getDate());

        tx.commit();
        session.close();
        return oldUr;
    }

}
