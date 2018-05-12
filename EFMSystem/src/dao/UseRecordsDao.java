package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dao.tables.UseRecords;
import dao.util.UtilDao;
import dao.util.UtilFactory;

@Repository
public class UseRecordsDao extends UtilDao<UseRecords> {

    public List<UseRecords> getAll() {
        return getAll("UseRecords");
    }

    public UseRecords getById(Integer id) {
        return getById("UseRecords", id);
    }
    
    public UseRecords update(Integer id, UseRecords newObj) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        UseRecords oldObj = (UseRecords) session.get(UseRecords.class, id);
        oldObj.setCurUsed(newObj.getCurUsed());
        oldObj.setDate(newObj.getDate());
        oldObj.setUserId(newObj.getUserId());

        tx.commit();
        session.close();
        return oldObj;
    }

    // 根据date和userId返回一条抄表记录
    public UseRecords getRecordsByDateAndUserId(String date, Integer userId) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from UseRecords where userId = ? and date = ?");
        query.setString(0, userId.toString());
        query.setString(1, date);
        System.out.println(query + "\tuserId=" + userId + "\tdate=" + date);
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

}
