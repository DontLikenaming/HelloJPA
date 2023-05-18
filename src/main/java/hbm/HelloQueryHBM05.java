package hbm;


import model.Department;
import model.Employee;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

import java.util.List;


public class HelloQueryHBM05 {
    public static void main(String[] args){
        // SessionFactory 초기화
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class).buildSessionFactory();
        Session sess = sf.openSession();

        try{
            // 조회 - 전체 사원
/*            Criteria query = sess.createCriteria(Employee.class);
            List<Employee> emps = query.list();

            System.out.println(emps);*/


            // 조회 - 전체 사원, 페이징 (6번째 사원부터 10명)
/*            Criteria query = sess.createCriteria(Employee.class);
            List<Employee> emps = query.setFirstResult(5).setMaxResults(10).list();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 조회 - 부분조회 1 : 이름, 연봉
/*            Criteria query = sess.createCriteria(Employee.class);
            query.setProjection(
                Projections.projectionList()
                .add(Projections.property("fname"))
                .add(Projections.property("sal"))
            );

            List<Object[]> items = query.list();

            for(Object[] item : items) {
                System.out.println("이름 : " + item[0] + ", 연봉 : " + item[1]);
            }*/


            // 조건 검색 : 직책이 IT_PROG인 사원 조회
/*            Criteria query = sess.createCriteria(Employee.class);
            query.add(Expression.eq("jobid", "IT_PROG"));

            List<Employee> emps = query.list();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 조건 검색 : 연봉이 20000 이상인 사원 조회
/*            Criteria query = sess.createCriteria(Employee.class);
            query.add(Expression.ge("sal", 20000));

            List<Employee> emps = query.list();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 정렬 조회 : 부서번호 기준, addOrder
/*            Criteria query = sess.createCriteria(Employee.class);
            query.addOrder(Order.asc("deptid"));

            List<Employee> emps = query.list();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 직책 수 조회
/*            Criteria query = sess.createCriteria(Employee.class);
            query.setProjection(
                Projections.projectionList()
                .add(Projections
                .countDistinct("jobid"))
            );

            List<Employee> cntjob = query.list();

            System.out.println(cntjob);*/


            // 그룹핑 : 직책별 최대, 최소, 평균 연봉, 직책 수 조회
/*            Criteria query = sess.createCriteria(Employee.class);
            query.setProjection(
                Projections.projectionList()
                .add(Projections.groupProperty("jobid"))
                .add(Projections.max("sal"))
                .add(Projections.min("sal"))
                .add(Projections.avg("sal"))
                .add(Projections.count("jobid"))
            );

            List<Object[]> items = query.list();

            for(Object[] item : items) {
                System.out.println("직책명 : " + item[0] + ", 최대 연봉 : " + item[1] + ", 최소 연봉 : " + item[2]
                        + ", 평균 연봉 : " + item[3] + ", 인원 수 : " + item[4]);
            }*/


            // 쿼리, 조건 조회 - 이름 부분 검색
/*            Criteria query = sess.createCriteria(Employee.class);
            // query.add(Expression.like("fname", "Steven"));   // 완전히 일치하는 문자열
            // query.add(Expression.like("fname", "eve"));      // eve가 포함된 문자열이 검색되지 않음
            // query.add(Expression.like("fname", "St", MatchMode.START)); // St%와 같은 효과
            // query.add(Expression.like("fname", "en", MatchMode.END)); // %en와 같은 효과
            query.add(Expression.like("fname", "eve", MatchMode.ANYWHERE)); // %eve%와 같은 효과

            List<Employee> emps = query.list();

            for(Employee emp : emps) {
                System.out.println(emp);
            }*/


            // 서브쿼리 1 : 부서명이 IT인 사원들의 이름, 직책, 입사일 조회
            // DetachedCriteria로 서브쿼리 정의 후
            // Criteria로 정의된 주 쿼리에 부착해서 실행
/*            DetachedCriteria subqry = DetachedCriteria.forClass(Department.class)
                                      .add(Restrictions.eq("dname", "IT"))
                                      .setProjection(Projections.property("deptid"));

            Criteria query = sess.createCriteria(Employee.class);

            query.add(
                Subqueries.propertyEq("deptid", subqry))
                .setProjection(Projections.projectionList()
                .add(Projections.property("fname"))
                .add(Projections.property("jobid"))
                .add(Projections.property("hdate"))
            );

            List<Object[]> items = query.list();

            for(Object[] item : items) {
                System.out.println("이름 : " + item[0] + ", 직책 : " + item[1] + ", 입사일 : " + item[2]);
            }*/


            // join : 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
            // FetchMode : 조인의 종류 정의
            // createAlias : 조인 대상 테이블 정의
/*            Criteria query = sess.createCriteria(Employee.class, "emp");
            query.setFetchMode("emp.department", FetchMode.JOIN);
            query.createAlias("emp.department", "dept");

            query.setProjection(
                Projections.projectionList()
                .add(Projections.property("fname"))
                .add(Projections.property("jobid"))
                .add(Projections.property("dept.dname"))
            );

            List<Object[]> items = query.list();

            for(Object[] item : items) {
                System.out.println("이름 : " + item[0] + ", 직책 : " + item[1] + ", 부서명 : " + item[2]);
            }*/


            // 동적 쿼리
            // 직책이 IT_PROG인 사원 조회
/*            String fname = null;
            String jobid = "IT_PROG";
            Integer sal = null;*/
            // 연봉이 10000 이상인 사원 조회
/*            String fname = null;
            String jobid = null;
            Integer sal = 10000;*/
            // 직책이 IT_PROG이면서 연봉이 6000 이상인 사원 조회
            String fname = null;
            String jobid = "IT_PROG";
            Integer sal = 6000;

            Criteria query = sess.createCriteria(Employee.class);
            if(fname != null) query.add(Expression.like("fname", fname, MatchMode.ANYWHERE));
            if(jobid != null) query.add(Expression.like("jobid", jobid));
            if(sal != null) query.add(Expression.ge("sal", sal));

            List<Employee> emps = query.list();

            for(Employee emp : emps) {
                System.out.println(emp);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            }
        }
}
