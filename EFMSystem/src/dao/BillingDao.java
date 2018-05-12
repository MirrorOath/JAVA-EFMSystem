package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dao.tables.Billing;
import dao.util.UtilDao;
import dao.util.UtilFactory;

@Repository
public class BillingDao extends UtilDao<Billing> {

    public List<Billing> getAll() {
        return getAll("Billing");
    }

    public Billing getById(Integer id) {
        return getById("Billing", id);
    }
    
    public Billing update(Integer id, Billing newObj) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Billing oldObj = (Billing) session.get(Billing.class, id);
        oldObj.setUserId(newObj.getUserId());
        oldObj.setTactics(newObj.getTactics());
        oldObj.setCurUsed(newObj.getCurUsed());
        oldObj.setCost(newObj.getCost());
        oldObj.setExCost(newObj.getExCost());
        oldObj.setDate(newObj.getDate());
        oldObj.setIsPaid(newObj.getIsPaid());

        tx.commit();
        session.close();
        return oldObj;
    }
    
    // 获得价格算法的所有规则
    @SuppressWarnings("unchecked")
    public List<Billing> getRulesByTactics(Integer tactics) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Billing where tactics = ?");
        query.setString(0, tactics.toString());
        List<Billing> rules = (List<Billing>) query.list();

        tx.commit();
        session.close();
        return rules;
    }
    
    public Billing updateById(Integer id, Billing oldBil) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Billing newBil = (Billing) session.get(Billing.class, id);
        newBil.setCost(oldBil.getCost());
        newBil.setExCost(oldBil.getExCost());
        newBil.setCurUsed(oldBil.getCurUsed());
        newBil.setDate(oldBil.getDate());
        newBil.setIsPaid(oldBil.getIsPaid());
        newBil.setTactics(oldBil.getTactics());
        newBil.setUserId(oldBil.getUserId());

        tx.commit();
        session.close();
        return newBil;
    }

    public Billing getByUserIdAndDate(String date, Integer userId) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Billing where userId = ? and date = ?");
        query.setString(0, userId.toString());
        query.setString(1, date);
        Billing bil = (Billing) query.uniqueResult();

        tx.commit();
        session.close();
        return bil;
    }

    private static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public void saved(Billing bil) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        if (getByUserIdAndDate(getStringDate(bil.getDate()), bil.getUserId()) != null)
            return;
        session.save(bil);

        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Billing> getByUserId(Integer userId) {
        Session session = UtilFactory.getSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Billing where userId = ?");
        query.setString(0, userId.toString());
        List<Billing> bils = (List<Billing>) query.list();

        tx.commit();
        session.close();
        return bils;
    }
}
