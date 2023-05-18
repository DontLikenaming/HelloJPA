package dsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Customers;
import model.QCustomers;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HelloQueryDSL01 {
    public static void main(String[] args){
        // jpa 객체 초기화 : emf(EntityManagerFactory) -> em(EntityManager) -> etx(EntityTransaction)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();

        try{
            // 쿼리 객체 준비
            QCustomers qcst = QCustomers.customers;
            JPAQueryFactory query = new JPAQueryFactory(em);

            // 조회
/*           List<Customers> cst = query.selectFrom(qcst).fetch();
            System.out.println(cst);*/

            // 필터링
/*            List<Customers> cst = query.selectFrom(qcst).where(qcst.userid.eq("apple")).fetch();
            System.out.println(cst);*/

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
            emf.close();
            }
        }
}
