package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


@Repository
public class UseResourcesDao {

    // 添加一条抄表记录
    public boolean addRecord(UseResources useResource) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        session.save(useResource);

        tx.commit();
        session.close();
        return true;
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
    
}
