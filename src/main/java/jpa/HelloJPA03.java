package jpa;

import model.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class HelloJPA03 {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            // 사원 데이터 조회 - TypedQuery
            // createQuery(질의문, 리턴되는 객체 종류)
/*            String jpql = "select e from Employee as e";
            List<Employee> emps = em.createQuery(jpql, Employee.class).getResultList();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/
            // 사원 데이터 조회  - Query : 이름, 부서번호, 입사일
            // createQuery(질의문)
/*            String jpql = "select fname, deptid, hdate from Employee e";
            List<Object[]> items = em.createQuery(jpql).getResultList();

            for(Object[] item : items){
                System.out.println(item[0] + " / " + item[1] + " / " + String.valueOf(item[2]).substring(0, 10));
            }*/

            // 사원 직책 조회 - jobid가 IT_PROG인 사원
            // 파라미터 바인딩 - :파라메터 이름, ?순번
            // String jpql = "select e from Employee e where jobid = :jobid"; // 이름 기반
/*             String jpql = "select e from Employee e where jobid = ?1"; // 위치 기반
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);

            // query.setParameter("jobid", "IT_PROG"); // 이름 기반
           query.setParameter(1, "IT_PROG"); // 위치 기반
            List<Employee> emps = query.getResultList();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/

            // 사원 임금의 평균
            /*String jpql = "select avg(sal) from Employee e";
            Double avgsal = em.createQuery(jpql, Double.class).getSingleResult();

            System.out.println(avgsal);*/

            // 사원 직책의 수 조회
/*            String jpql = "select count(distinct(jobid)) from Employee e";
            Long cntjob = em.createQuery(jpql, Long.class).getSingleResult();

            System.out.println(cntjob);*/

            // empid로 정렬 후 3페이지 조회 : 페이징 (페이지 당 출력 건수 : 15)
            // setFirstResult(startpos) : 페이징 시작 위치
            // setMaxResult(getdatacnt) : 조회할 데이터 수
/*            String jpql = "select e from Employee e order by empid";
            List<Employee> pemps = em.createQuery(jpql, Employee.class).setFirstResult(30).setMaxResults(15).getResultList();

            for(Employee emp : pemps) {
                System.out.println(emp);
            }*/

            // 직책별 평균 연봉과 사원수 조회
/*            String jpql = "select jobid, avg(sal), count(jobid) from Employee e group by jobid";
            List<Object[]> items = em.createQuery(jpql).getResultList();

            for(Object[] item : items){
                System.out.println(item[0] + " 평균 연봉 : $" + item[1] + " 인원 수 : " + item[2] +" 명");
            }*/

            // 사원 이름과 직책, 부서 이름 조회 : join
/*            String jpql = " select e.fname, e.jobid, d.dname from Employee e " +
                    " inner join e.department d order by e.fname asc ";
            List<Object[]> items = em.createQuery(jpql).getResultList();

            for(Object[] item : items){
                System.out.println("성명 : " + item[0] + " 직책 : " + item[1] + " 부서명 : " + item[2]);
            }*/

            // 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회 : join
/*            String jpql = " select e.fname, e.jobid, d.dname from Employee e " +
                    " inner join e.department d where e.deptid = 60 order by e.fname asc ";
            List<Object[]> items = em.createQuery(jpql).getResultList();

            System.out.println("부서번호 60번인 사원");
            for(Object[] item : items){
                System.out.println("성명 : " + item[0] + " 직책 : " + item[1] + " 부서명 : " + item[2]);
            }*/

            // 부서명이 IT 인 사원의 사번과 입사일 조회 : 서브쿼리
/*            String jpql = " select empid, hdate from Employee e where deptid = " +
                          " (select deptid from Department d where dname like 'IT') ";

            List<Object[]> items = em.createQuery(jpql).getResultList();

            System.out.println("부서명이 IT인 사원");
            for(Object[] item : items){
                System.out.println("사번 : " + item[0] + " 입사일 : " + item[1]);
            }*/

            // 제공된 이름, 직책, 연봉으로 사원 조회 : 동적 쿼리
/*            // 직책이 IT_PROG 인 사원 조회
            String fname = null;
            String jobid = "IT_PROG";
            Integer sal = null;    // null 체크를 위해 클래스형으로 선언*/

            // 연봉이 10000 이상인 사원 조회
/*            String fname = null;
            String jobid = null;
            Integer sal = 10000;*/

            // 직책이 IT_PROG이면서 연봉이 6000 이상인 사원 조회
            String fname = null;
            String jobid = "IT_PROG";
            Integer sal = 6000;

            String jqpl = " select e from Employee e ";
            List<String> cndtns = new ArrayList<>(); // 조건절 저장 변수

            if(fname != null){
                cndtns.add(" fname like concat('%', :fname, '%') ");
            }

            if(jobid != null){
                cndtns.add(" jobid = :jobid ");
            }

            if(sal != null){
                cndtns.add(" sal >= :sal ");
            }

            if(!cndtns.isEmpty()){  // 조건식이 하나라도 존재한다면
                jqpl += " where " + String.join(" and ", cndtns);
            }

            TypedQuery<Employee> query = em.createQuery(jqpl, Employee.class);

            if(fname != null){
                query.setParameter("fname", fname);
            }

            if(jobid != null){
                query.setParameter("jobid", jobid);
            }

            if(sal != null){
                query.setParameter("sal", sal);
            }

            List<Employee> emps = query.getResultList();

            for(Employee e : emps){
                System.out.println(e);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
            emf.close();
            }
        }
}
