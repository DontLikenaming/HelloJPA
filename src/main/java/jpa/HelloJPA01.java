package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJPA01 {
    public static void main(String[] args){
        // jpa 객체 초기화 : emf(EntityManagerFactory) -> em(EntityManager) -> etx(EntityTransaction)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
            emf.close();
            }
        }
}
