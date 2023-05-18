package hbm;

import model.Customers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class HelloQueryHBM01 {
    public static void main(String[] args){
        // SessionFactory 초기화
        String fpath = "src/main/resources/mapping/customers.hbm.xml";
        Configuration cfg = new Configuration().configure();
        // SessionFactory sf = cfg.addFile(fpath).buildSessionFactory();
        SessionFactory sf = cfg.addAnnotatedClass(Customers.class).buildSessionFactory();
        Session sess = sf.openSession();

        try{
            // hql을 이용한 조회
            Query query = sess.createQuery("from Customers");
            List sjs = query.list();

            // System.out.println(sjs);

            query = sess.createQuery("from Customers where userid = ?1");
            query.setParameter(1, "orange");
            sjs = query.list();

            System.out.println(sjs);

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            }
        }
}
