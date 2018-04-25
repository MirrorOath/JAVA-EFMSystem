package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dao.tables.Billing;
import dao.util.UtilFactory;

@Repository
public class BillingDao {

    // 获得价格算法的所有规则
    @SuppressWarnings("unchecked")
    public List<Billing> getRulesByTactics(Integer tactics){
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("from BillingStrategy where tactics = ?");
        query.setString(0, tactics.toString());
        List<Billing> rules = (List<Billing>) query.list();
        
        tx.commit();
        session.close();
        return rules;
    }
}
