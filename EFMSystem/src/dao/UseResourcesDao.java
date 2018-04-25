package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dao.tables.UseResources;
import dao.util.UtilFactory;


@Repository
public class UseResourcesDao {

    // 添加一条抄表记录
    public UseResources addRecord(UseResources useResource) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        session.save(useResource);

        tx.commit();
        session.close();
        return useResource;
    }
    
    // 根据用户id返回所有抄表记录
    @SuppressWarnings("unchecked")
    public List<UseResources> getRecordsByUserId(Integer user_id){
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("from UseResources where user_id = ?");
        query.setString(0, user_id.toString());
        List<UseResources> Records = (List<UseResources>) query.list();
        
        tx.commit();
        session.close();
        return Records;
    }
    
    @SuppressWarnings("unchecked")
    public List<UseResources> getAllMeters(){
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("from UseResources");
        List<UseResources> Records = (List<UseResources>) query.list();

        tx.commit();
        session.close();
        return Records;
    }
    
    public UseResources update(Integer id, UseResources us) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        UseResources ur = (UseResources) session.get(UseResources.class, id);
        ur.setCur_used(us.getCur_used());
        ur.setUser_id(us.getUser_id());
        ur.setRcd_time(us.getRcd_time());
        
        tx.commit();
        session.close();
        return ur;
    }
    
}
