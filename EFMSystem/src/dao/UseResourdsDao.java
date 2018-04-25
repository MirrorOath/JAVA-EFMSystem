package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dao.tables.UseResourds;
import dao.util.UtilFactory;


@Repository
public class UseResourdsDao {

    // 添加一条抄表记录
    public UseResourds addRecord(UseResourds useResource) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        session.save(useResource);

        tx.commit();
        session.close();
        return useResource;
    }
    
    // 根据用户id返回所有抄表记录
    @SuppressWarnings("unchecked")
    public List<UseResourds> getRecordsByUserId(Integer user_id){
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("from UseResources where user_id = ?");
        query.setString(0, user_id.toString());
        List<UseResourds> Records = (List<UseResourds>) query.list();
        
        tx.commit();
        session.close();
        return Records;
    }
    
    @SuppressWarnings("unchecked")
    public List<UseResourds> getAllMeters(){
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("from UseResources");
        List<UseResourds> Records = (List<UseResourds>) query.list();

        tx.commit();
        session.close();
        return Records;
    }
    
    public UseResourds update(Integer id, UseResourds newUr) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        UseResourds oldUr = (UseResourds) session.get(UseResourds.class, id);
        oldUr.setCurUsed(newUr.getCurUsed());
        oldUr.setUserId(newUr.getUserId());
        oldUr.setDate(newUr.getDate());
        
        tx.commit();
        session.close();
        return oldUr;
    }
    
}
