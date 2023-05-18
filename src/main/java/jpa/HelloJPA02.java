package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJPA02 {
    public static void main(String[] args){
        // jpa 객체 초기화 : emf(EntityManagerFactory) -> em(EntityManager) -> etx(EntityTransaction)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            /*// 데이터 추가 : persist(대상)
            SungJuk sj = new SungJuk();
            sj.setName("삼녀");
            sj.setKor(99);
            sj.setEng(100);
            sj.setMat(99);

            tx.begin();
                em.persist(sj);
            tx.commit();

            // 데이터 조회 : find(객체명.class, 기본키 값)
            tx.begin();
                sj = em.find(SungJuk.class, 3);
                System.out.println(sj);
            tx.commit();

            // 데이터 수정 : 대상 조회 후, setXxx(변경하려는 값) -> persist로 입력
            tx.begin();
                sj = em.find(SungJuk.class, 2);
                sj.setName("삼남");
                em.persist(sj);
            tx.commit();

            // 데이터 삭제 : 대상 조회 후, remove(대상)
            tx.begin();
                sj = em.find(SungJuk.class, 4);
                em.remove(sj);
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
