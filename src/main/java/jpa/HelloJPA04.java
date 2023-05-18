package jpa;

import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

public class HelloJPA04 {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadbJPA");
        EntityManager em = emf.createEntityManager();

        try{
            // Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // 사원 테이블 조회
/*            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);   // 조회 대상 지정
            Root<Employee> e = query.from(Employee.class);
            CriteriaQuery<Employee> cq = query.select(e);

            List<Employee> emps = em.createQuery(cq.select(e)).getResultList();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/

            // 사원 데이터 조회 - 이름, 부서번호, 입사일 : multiselect
            // 컬럼 지정 : 객체.get(변수명)
/*            CriteriaQuery<Object[]> mcq = cb.createQuery(Object[].class);
            Root<Employee> me = mcq.from(Employee.class);

            mcq.multiselect(me.get("fname"), me.get("deptid"), me.get("hdate"));
            List<Object[]> items = em.createQuery(mcq).getResultList();

            for(Object[] item : items) {
                System.out.println("이름 : " + item[0] + " 부서번호 : " + item[1] + " 입사일 : " + item[2]);
            }*/

            // 정렬 조회 : 부서 번호 기준, orderby
/*            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);
            CriteriaQuery<Employee> cq;

            Order deptid = cb.desc(e.get("deptid"));
            cq = query.select(e).orderBy(deptid);

            List<Employee> emps = em.createQuery(cq).getResultList();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/

            // 조건 검색 : 직책이 IT_PROG인 사원 조회, where
/*            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);
            CriteriaQuery<Employee> cq;

            Order fname = cb.asc(e.get("fname"));
            Predicate jobid = cb.equal(e.get("jobid"), "IT_PROG");
            cq = query.select(e).where(jobid).orderBy(fname);

            List<Employee> emps = em.createQuery(cq).getResultList();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/

            // 조건 검색 : 연봉이 20000 이상인 사원 조회
/*            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);
            CriteriaQuery<Employee> cq;

            Order fname = cb.asc(e.get("fname"));
            Predicate salGE = cb.ge(e.get("sal"), 20000);
            cq = query.select(e).where(salGE).orderBy(fname);

            List<Employee> emps = em.createQuery(cq).getResultList();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/

            // 직책 수 조회 1
/*            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);
            CriteriaQuery<Employee> cq;

            Expression cntJob = cb.count(e.<Integer>get("jobid"));
            cq = query.select(cntJob);

            List<Employee> cnt = em.createQuery(cq).getResultList();

            System.out.println(cnt);*/

            // 직책 수 조회 2 : distinct
/*            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);
            CriteriaQuery<Employee> cq;

            cq = query.select(e.get("jobid")).distinct(true);

            List<Employee> cnt = em.createQuery(cq).getResultList();

            System.out.println(cnt);*/

            // 직책 수 조회 3 : countDistinct
/*            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);
            CriteriaQuery<Employee> cq;

            Expression cntJob = cb.countDistinct(e.<Integer>get("jobid"));
            cq = query.select(cntJob);

            List<Employee> cnt = em.createQuery(cq).getResultList();

            System.out.println(cnt);*/

            // 그룹핑 : 직책별 최대, 최소, 평균 급여, 직책 수 조회
/*            CriteriaQuery<Object[]> gcb = cb.createQuery(Object[].class);
            Root<Employee> ge = gcb.from(Employee.class);

            Order jobid = cb.asc(ge.get("jobid"));
            Expression maxSal = cb.max(ge.get("sal"));
            Expression minSal = cb.min(ge.get("sal"));
            Expression avgSal = cb.avg(ge.get("sal"));
            Expression cntSal = cb.count(ge.get("sal"));

            gcb.multiselect(ge.get("jobid"), maxSal, minSal, avgSal, cntSal).orderBy(jobid);
            gcb.groupBy(ge.get("jobid"));

            List<Object[]> items = em.createQuery(gcb).getResultList();

            for(Object[] item : items) {
                System.out.println("직책명 : " + item[0] + " 최대 급여 : " + item[1]
                        + " 최소 급여 : " + item[2] + " 평균 급여 : " + item[3] + " 직책수 : " + item[4]);
            }*/

            // 서브쿼리 1 : 평균 연봉보다 작게 받는 사원들 조회
            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);

            // 하위 쿼리
            Subquery<Double> qryAsal = query.subquery(Double.class);
            Root<Employee> se = qryAsal.from(Employee.class);
            qryAsal.select(cb.avg(se.get("sal")));

            // 주 쿼리
            query.select(e).where(cb.lt(e.get("sal"), qryAsal));
            List<Employee> emps = em.createQuery(query).getResultList();

            for(Employee emp : emps){
                System.out.println(emp);
            }



            // 서브쿼리 2 : 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
/*            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Employee> e = cq.from(Employee.class);

            Join<Employee, Department> jnObj = e.join("department", JoinType.INNER);

            // 조인 결과에서 원하는 컬럼 추출하는 질의문 작성
            CriteriaQuery<Object[]> jnq = cq.multiselect(e.get("fname"),
                    e.get("jobid"), jnObj.get("dname")).where(cb.equal(e.get("deptid"), 60));

            List<Object[]> items = em.createQuery(jnq).getResultList();

            for(Object[] item : items) {
                System.out.println(item[0]+" "+item[1]+" "+item[2]);
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
            /*String fname = null;
            String jobid = "IT_PROG";
            Integer sal = 6000;

            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);
            List<Predicate> predicates = new ArrayList<>();  // 조건절 저장 변수

            if(fname != null){
                predicates.add(cb.like(e.get("fname"), "%" + fname + "%"));
            }

            if(jobid != null){
                predicates.add(cb.equal(e.get("jobid"), jobid));
            }

            if(sal != null){
                predicates.add(cb.ge(e.get("sal"), sal));
            }

            //query.where(predicates.toArray(new Predicate[0]));
            query.where(cb.or(predicates.toArray(new Predicate[0])));

            List<Employee> emps = em.createQuery(query).getResultList();

            for(Employee emp : emps){
                System.out.println(emp);
            }*/

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
            emf.close();
            }
        }
}
