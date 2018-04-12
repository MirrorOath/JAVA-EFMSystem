package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class BillingStrategyDao {

    // 获得价格算法的所有规则
    @SuppressWarnings("unchecked")
    public List<BillingStrategy> getRulesByTactics(Integer tactics){
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.createQuery("from BillingStrategy where tactics = ?");
        query.setString(0, tactics.toString());
        List<BillingStrategy> rules = (List<BillingStrategy>) query.list();
        
        tx.commit();
        session.close();
        return rules;
    }
}
