package jpa;

import model.Zipcode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HelloJPA03_5 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();


        try {
            /*String jqpl = "select z from Zipcode z";
            List<Zipcode> zips = em.createQuery(jqpl, Zipcode.class).getResultList();

            for(Employees emp : emps) {
                System.out.println(emp);
            }*/

            /*String jqpl = "select sido, gugun, dong from Zipcode z";
            List<Object[]> zips = em.createQuery(jqpl).getResultList();

            for(Object[] zip : zips){
                System.out.println(zip[0]+" "+zip[1]+" "+zip[2]);
            }*/

/*            String jqpl = "select dong, ri, bunji from Zipcode z where dong like ?1";
            TypedQuery<Object[]> query = em.createQuery(jqpl, Object[].class);

            query.setParameter(1, "가산동");
            List<Object[]> zips = query.getResultList();

            for(Object[] zip : zips){
                if(zip[1]==null){
                    zip[1] = "";
                }

                if(zip[2]==null){
                    zip[2] = "";
                }
                System.out.println(zip[0]+" "+zip[1]+" "+zip[2]);
            }*/

/*            String jqpl = "select count(distinct(gugun)) from Zipcode z";
            List<Long> cntgugun = em.createQuery(jqpl, Long.class).getResultList();

            for(Long gugun : cntgugun) {
                System.out.println(gugun);
            }*/

/*            String jqpl = "select sido, count(distinct(gugun)) from Zipcode z group by sido order by count(distinct(gugun)) desc";
            List<Object[]> cntgugun = em.createQuery(jqpl, Object[].class).getResultList();

            for(Object[] gugun : cntgugun) {
                System.out.println(gugun[0]+" : "+gugun[1]+" 개");
            }*/

            String jqpl = "select z from Zipcode z order by seq asc";
            List<Zipcode> zips = em.createQuery(jqpl, Zipcode.class).setFirstResult(120).setMaxResults(15).getResultList();

            for(Zipcode zip : zips) {
                System.out.println(zip);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            etx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}