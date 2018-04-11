package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilFactory {
    private static SessionFactory sf=null;
    static{
    	sf=new Configuration().configure().buildSessionFactory();
    }
	public static Session getSession(){
	   return sf.openSession();
	}
}