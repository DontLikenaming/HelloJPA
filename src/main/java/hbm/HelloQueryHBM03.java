package hbm;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class HelloQueryHBM03 {
    public static void main(String[] args){
        // jpa 설정 파일 읽어옴
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();

        // jpa에서 생성된 em을 session 객체로 전환
        Session session = em.unwrap(Session.class);
        SessionFactory sf = session.getSessionFactory();
        Session sess = sf.openSession();

        try{
            // hql을 이용한 조회
            Query query = sess.createQuery("from Customers ");
            List sjs = query.list();

             System.out.println(sjs);

            /*query = sess.createQuery("from Customers where userid = ?1");
            query.setParameter(1, "orange");
            sjs = query.list();

            System.out.println(sjs);*/

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            sess.close();
            sf.close();
            }
        }
}
