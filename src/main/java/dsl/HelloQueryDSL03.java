package dsl;



import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloQueryDSL03 {
    public static void main(String[] args){
        // jpa 객체 초기화 : emf(EntityManagerFactory) -> em(EntityManager) -> etx(EntityTransaction)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{


            // 사원 정보 추가
/*            tx.begin();

            Employee e = new Employee();
            em.persist(e);

            tx.commit();*/

            // 사원 정보 수정
/*            QEmployee qemp = QEmployee.employee;
            JPAQueryFactory query = new JPAQueryFactory(em);
            tx.begin();

            query.update(qemp)
                    .set(qemp.fname, "John")
                    .set(qemp.lname, "Doe")
                    .set(qemp.sal, 15500)
                    .set(qemp.jobid, "Game_QC")
                    .where(qemp.empid.eq(207L))
                    .execute();

            tx.commit();*/

            // 사원 정보 삭제
/*            QEmployee qemp = QEmployee.employee;
            JPAQueryFactory query = new JPAQueryFactory(em);
            tx.begin();

            query.delete(qemp)
                    .where(qemp.empid.eq(207L))
                    .execute();

            tx.commit();*/

        }catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
            emf.close();
            }
        }
}
